package com.draken.app_movil_pm.core.filemanager.data

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat
import com.draken.app_movil_pm.core.filemanager.domain.FileManagerRepository
import com.draken.app_movil_pm.core.filemanager.model.FileInfo
import com.draken.app_movil_pm.core.filemanager.model.ImageDimensions
import com.draken.app_movil_pm.core.filemanager.model.ValidationResult
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileManager(private val context: Context): FileManagerRepository {

    companion object {
        // Permisos correctos para diferentes versiones de Android
        val REQUIRED_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        // Tipos de archivo soportados
        private val SUPPORTED_IMAGE_TYPES = setOf(
            "image/jpeg",
            "image/jpg",
            "image/png",
            "image/webp",
            "image/bmp"
        )
    }

    /**
     * Convierte una URI a MultipartBody.Part para envío por API
     */
    override fun uriToMultipartBody(uri: Uri, partName: String): MultipartBody.Part? {
        return try {
            // Validar la URI primero
            val validationResult = validateImageUri(uri)
            if (!validationResult.isValid) {
                throw Exception(validationResult.errorMessage)
            }

            val inputStream = context.contentResolver.openInputStream(uri)
            val fileInfo = getFileInfoFromUri(uri)
            val file = createTempFile(fileInfo.extension)

            inputStream?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }

            val requestBody = file.asRequestBody(fileInfo.mimeType.toMediaTypeOrNull())
            MultipartBody.Part.createFormData(partName, fileInfo.fileName, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Verifica si los permisos necesarios están concedidos
     */
    override fun hasRequiredPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Obtiene la lista de permisos requeridos para la versión actual de Android
     */
    fun getRequiredPermissions(): Array<String> = REQUIRED_PERMISSIONS

    /**
     * Valida si una URI contiene una imagen válida
     */
    override fun validateImageUri(uri: Uri): ValidationResult {
        try {
            val fileInfo = getFileInfoFromUri(uri)

            // Verificar tipo de archivo
            if (!SUPPORTED_IMAGE_TYPES.contains(fileInfo.mimeType)) {
                return ValidationResult(
                    false,
                    "Tipo de archivo no soportado. Use: ${SUPPORTED_IMAGE_TYPES.joinToString(", ")}"
                )
            }

            // Verificar que se puede leer la imagen
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            if (bitmap == null) {
                return ValidationResult(false, "No se pudo leer la imagen. Archivo corrupto.")
            }

            bitmap.recycle()
            return ValidationResult(true, null)

        } catch (e: Exception) {
            return ValidationResult(false, "Error al validar la imagen: ${e.message}")
        }
    }

    /**
     * Obtiene información detallada de un archivo desde su URI
     */
    override fun getFileInfoFromUri(uri: Uri): FileInfo {
        var fileName = "unknown"
        var fileSize = 0L
        var mimeType = "application/octet-stream"

        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)

            if (cursor.moveToFirst()) {
                if (nameIndex != -1) {
                    fileName = cursor.getString(nameIndex) ?: "unknown"
                }
                if (sizeIndex != -1) {
                    fileSize = cursor.getLong(sizeIndex)
                }
            }
        }

        // Obtener MIME type
        mimeType = context.contentResolver.getType(uri) ?: "application/octet-stream"

        // Extraer extensión del nombre del archivo
        val extension = fileName.substringAfterLast(".", "jpg")

        return FileInfo(
            fileName = fileName,
            size = fileSize,
            mimeType = mimeType,
            extension = extension
        )
    }

    /**
     * Crea un archivo temporal con la extensión especificada
     */
    private fun createTempFile(extension: String): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return File(context.cacheDir, "temp_image_${timestamp}.${extension}")
    }

    /**
     * Limpia archivos temporales antiguos (más de 1 hora)
     */
    override fun cleanupTempFiles() {
        try {
            val cacheDir = context.cacheDir
            val currentTime = System.currentTimeMillis()
            val oneHour = 60 * 60 * 1000L

            cacheDir.listFiles()?.forEach { file ->
                if (file.name.startsWith("temp_image_") || file.name.startsWith("optimized_")) {
                    if (currentTime - file.lastModified() > oneHour) {
                        file.delete()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Obtiene las dimensiones de una imagen desde su URI
     */
    override fun getImageDimensions(uri: Uri): ImageDimensions? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()

            ImageDimensions(options.outWidth, options.outHeight)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Verifica si una URI apunta a un archivo existente y accesible
     */
    override fun isUriAccessible(uri: Uri): Boolean {
        return try {
            context.contentResolver.openInputStream(uri)?.use { true } ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Convierte bytes a formato legible (KB, MB)
     */
    override fun formatFileSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "${bytes} B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            else -> "${"%.1f".format(bytes / (1024.0 * 1024.0))} MB"
        }
    }
}
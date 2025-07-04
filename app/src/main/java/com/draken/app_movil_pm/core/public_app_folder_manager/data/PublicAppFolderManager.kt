package com.draken.app_movil_pm.core.public_app_folder_manager.data

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.model.AppImage
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException


class PublicAppFolderManager(private val context: Context): PublicAppFolderManagerRepository {

    companion object {
        // 📁 Nombre de la carpeta de tu app (personalízalo)
        const val APP_FOLDER_NAME = "MiApp"  // Cambia esto por el nombre de tu app

        // Subcarpetas opcionales para organizar mejor
        const val SUBFOLDER_PHOTOS = "Fotos"
        const val SUBFOLDER_DOCUMENTS = "Documentos"
        const val SUBFOLDER_PROFILE = "Perfil"
    }

    // 🎯 METODO PRINCIPAL: Crear URI directo en carpeta de la app
    override fun createAppFolderUri(
        fileName: String,
        subfolder: String?
    ): Uri? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            createAppFolderUriModern(fileName, subfolder)
        } else {
            createAppFolderUriLegacy(fileName, subfolder)
        }
    }

    // 🎯 FINALIZAR IMAGEN: Marcar como completada
    override fun finalizeImage(uri: Uri): Boolean {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Marcar como completada (ya no está pendiente)
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.IS_PENDING, 0)
                }
                context.contentResolver.update(uri, contentValues, null, null)
                true
            } else {
                // Notificar al scanner para que aparezca en galería
                val path = uri.path
                if (path != null) {
                    MediaScannerConnection.scanFile(
                        context,
                        arrayOf(path),
                        arrayOf("image/jpeg"),
                        null
                    )
                }
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    // 📋 OBTENER LISTA DE IMÁGENES DE LA APP
    override suspend fun getAppImages(): List<AppImage> = withContext(Dispatchers.IO) {
        val images = mutableListOf<AppImage>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Consultar MediaStore para imágenes en nuestra carpeta
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.RELATIVE_PATH,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATE_MODIFIED
            )

            val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
            val selectionArgs = arrayOf("%$APP_FOLDER_NAME%")

            context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val path = cursor.getString(pathColumn)
                    val size = cursor.getLong(sizeColumn)
                    val date = cursor.getLong(dateColumn)

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    images.add(
                        AppImage(
                            uri = uri,
                            name = name,
                            path = path,
                            size = size,
                            dateModified = date
                        )
                    )
                }
            }
        } else {
            // Para Android 9-, buscar en directorio físico
            val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val appDir = File(picturesDir, APP_FOLDER_NAME)

            if (appDir.exists()) {
                appDir.walkTopDown().forEach { file ->
                    if (file.isFile && file.extension.lowercase() in listOf("jpg", "jpeg", "png")) {
                        images.add(
                            AppImage(
                                uri = Uri.fromFile(file),
                                name = file.name,
                                path = file.parent ?: "",
                                size = file.length(),
                                dateModified = file.lastModified()
                            )
                        )
                    }
                }
            }
        }

        images
    }

    // 🗑️ ELIMINAR IMAGEN DE LA APP
    override suspend fun deleteAppImage(uri: Uri): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            context.contentResolver.delete(uri, null, null) > 0
        } catch (e: Exception) {
            false
        }
    }

    override fun getAppFolderPath(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            "Pictures/$APP_FOLDER_NAME/"
        } else {
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/$APP_FOLDER_NAME/"
        }
    }

    // 📱 ANDROID 10+ (API 29+) - Usando MediaStore
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createAppFolderUriModern(fileName: String, subfolder: String?): Uri? {
        val relativePath = if (subfolder != null) {
            "${Environment.DIRECTORY_PICTURES}/$APP_FOLDER_NAME/$subfolder"
        } else {
            "${Environment.DIRECTORY_PICTURES}/$APP_FOLDER_NAME"
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
            put(MediaStore.Images.Media.IS_PENDING, 1) // Importante: marcar como pendiente
        }

        return try {
            context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
        } catch (e: Exception) {
            null
        }
    }

    // 📱 ANDROID 9- (API 28-) - Usando File directo
    private fun createAppFolderUriLegacy(fileName: String, subfolder: String?): Uri? {
        if (!hasWritePermission()) {
            return null
        }

        return try {
            val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val appDir = if (subfolder != null) {
                File(picturesDir, "$APP_FOLDER_NAME/$subfolder")
            } else {
                File(picturesDir, APP_FOLDER_NAME)
            }

            // Crear directorio si no existe
            if (!appDir.exists()) {
                appDir.mkdirs()
            }

            val file = File(appDir, fileName)

            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            null
        }
    }

    // 🛠️ MÉTODOS AUXILIARES
    private fun hasWritePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true // Android 10+ no necesita permiso para MediaStore
        } else {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}
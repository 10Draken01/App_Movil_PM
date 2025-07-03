// CamaraManager.kt - Agregar la función faltante
package com.example.projecto.core.hardware.data

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CamaraManager(private val context: Context) {

    companion object {
        const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
        const val WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    }

    // Crear archivo temporal para la foto
    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    // Obtener URI para el archivo
    fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    // Verificar si el dispositivo tiene cámara
    fun hasCamera(): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    // Verificar permisos de cámara
    fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            CAMERA_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // 👈 AGREGAR ESTA FUNCIÓN QUE FALTA
    fun createTempImageUri(): Uri? {
        return try {
            val file = createImageFile()
            getUriForFile(file)
        } catch (e: IOException) {
            null
        }
    }

    // Estas funciones retornan Result porque la implementación real
    // se hará en el Composable con los launchers
    suspend fun takePhoto(): Result<Uri> {
        return try {
            if (!hasCamera()) {
                Result.failure(Exception("El dispositivo no tiene cámara"))
            } else if (!hasCameraPermission()) {
                Result.failure(Exception("Permiso de cámara no concedido"))
            } else {
                // El URI se generará en el Composable con el launcher
                val file = createImageFile()
                val uri = getUriForFile(file)
                Result.success(uri)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun selectFromGallery(): Result<Uri> {
        return try {
            // Similar al takePhoto, la implementación real será en el Composable
            Result.failure(Exception("Debe implementarse en el Composable con launcher"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
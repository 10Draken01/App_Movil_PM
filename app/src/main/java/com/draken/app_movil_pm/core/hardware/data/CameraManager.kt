package com.draken.app_movil_pm.core.hardware.data

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import java.io.File
import java.io.IOException

class CameraManager(private val context: Context): CameraManagerRepository {
    companion object {
        const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    private fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    override suspend fun hasCamera(): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    override suspend fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            CAMERA_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun createUriImage(filename: String): Uri? {
        return try {
            val file = createImageFile(filename)
            getUriForFile(file)
        } catch (e: IOException) {
            null
        }
    }
}
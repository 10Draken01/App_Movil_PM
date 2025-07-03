package com.draken.app_movil_pm.core.hardware.domain


import android.net.Uri
import java.io.File

interface CamaraRepository {
    fun hasCameraPermission(): Boolean
    fun getCameraPermission(): Boolean
    fun createTempImageUri(): Uri?
    suspend fun takePhoto(): Result<File>
}
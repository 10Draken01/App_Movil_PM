package com.draken.app_movil_pm.core.hardware.domain


import android.net.Uri

interface CamaraRepository {
    suspend fun takePhoto(): Result<Uri>
    suspend fun selectFromGallery(): Result<Uri>
    fun hasCamera(): Boolean
    fun hasCameraPermission(): Boolean
    fun createTempImageUri(): Uri?
}
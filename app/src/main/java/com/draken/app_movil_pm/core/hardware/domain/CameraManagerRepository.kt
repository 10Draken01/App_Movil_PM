package com.draken.app_movil_pm.core.hardware.domain


import android.net.Uri
import java.io.File

interface CameraManagerRepository {
    suspend fun hasCamera(): Boolean
    suspend fun hasCameraPermission(): Boolean
}
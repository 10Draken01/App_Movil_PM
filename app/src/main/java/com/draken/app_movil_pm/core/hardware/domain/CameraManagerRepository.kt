package com.draken.app_movil_pm.core.hardware.domain

interface CameraManagerRepository {
    suspend fun hasCamera(): Boolean
    suspend fun hasCameraPermission(): Boolean
}
package com.draken.app_movil_pm.core.hardware.data

import android.net.Uri
import com.draken.app_movil_pm.core.hardware.domain.CamaraRepository

class CamaraRepositoryImpl(
    private val camaraManager: CamaraManager
) : CamaraRepository {

    override suspend fun takePhoto(): Result<Uri> {
        return camaraManager.takePhoto()
    }

    override suspend fun selectFromGallery(): Result<Uri> {
        return camaraManager.selectFromGallery()
    }

    override fun hasCamera(): Boolean {
        return camaraManager.hasCamera()
    }

    override fun hasCameraPermission(): Boolean {
        return camaraManager.hasCameraPermission()
    }

    override fun createTempImageUri(): Uri? {
        return camaraManager.createTempImageUri()
    }
}
package com.draken.app_movil_pm.features.agregar_cliente.domain.usecase

import android.net.Uri
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository

class TakePhotoUseCase(private val repository: CameraManagerRepository) {
    suspend operator fun invoke(): Uri {
        return repository.takePhoto()
    }
}
package com.draken.app_movil_pm.features.agregar_cliente.domain.usecase

import android.net.Uri
import com.draken.app_movil_pm.core.hardware.domain.CamaraRepository
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Character_Icon
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository

class TakePhotoUseCase(private val repository: CamaraRepository) {
    suspend operator fun invoke(): Uri {
        return repository.takePhoto()
    }
}
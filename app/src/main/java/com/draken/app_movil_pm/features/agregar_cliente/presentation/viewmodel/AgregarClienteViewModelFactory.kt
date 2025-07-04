package com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.core.hardware.data.CameraManager
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import com.draken.app_movil_pm.core.public_app_folder_manager.data.PublicAppFolderManager
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase
class AgregarClienteViewModelFactory(
    private val agregarClienteUseCase: AgregarClienteUseCase,
    private val cameraManagerRepository: CameraManagerRepository,
    private val publicAppFolderManagerRepository: PublicAppFolderManagerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgregarClienteViewModel(
            agregarClienteUseCase,
            cameraManagerRepository,
            publicAppFolderManagerRepository
        ) as T
    }
}
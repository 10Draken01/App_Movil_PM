package com.draken.app_movil_pm.features.editar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import com.draken.app_movil_pm.core.hardware.domain.VibratorRepository
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository
import com.draken.app_movil_pm.features.editar_cliente.domain.usecase.EditarClienteUseCase

class EditarClienteViewModelFactory(
    private val editarClienteUseCase: EditarClienteUseCase,
    private val cameraManagerRepository: CameraManagerRepository,
    private val publicAppFolderManagerRepository: PublicAppFolderManagerRepository,
    private val vibratorRepository: VibratorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditarClienteViewModel(
            editarClienteUseCase = editarClienteUseCase,
            cameraManagerRepository = cameraManagerRepository,
            publicAppFolderManagerRepository = publicAppFolderManagerRepository,
            vibratorRepository = vibratorRepository
        ) as T
    }
}
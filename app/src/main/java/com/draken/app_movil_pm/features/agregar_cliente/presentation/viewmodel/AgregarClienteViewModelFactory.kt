package com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.core.add_cliente_service.domain.usecase.AddClienteUseCase
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import com.draken.app_movil_pm.core.hardware.domain.VibratorRepository
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository
import com.draken.app_movil_pm.core.rooms.domain.usecase.AddLocalClienteUseCase
import com.draken.app_movil_pm.core.domain.usecase.AgregarClienteUseCase
class AgregarClienteViewModelFactory(
    private val addClienteUseCase: AddClienteUseCase,
    private val addLocalClienteUseCase: AddLocalClienteUseCase,
    private val cameraManagerRepository: CameraManagerRepository,
    private val publicAppFolderManagerRepository: PublicAppFolderManagerRepository,
    private val vibratorRepository: VibratorRepository,
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgregarClienteViewModel(
            addClienteUseCase = addClienteUseCase,
            addLocalClienteUseCase = addLocalClienteUseCase,
            cameraManagerRepository = cameraManagerRepository,
            publicAppFolderManagerRepository = publicAppFolderManagerRepository,
            vibratorRepository = vibratorRepository,
            connectivityMonitorRepository = connectivityMonitorRepository
        ) as T
    }
}
package com.draken.app_movil_pm.features.clientes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.rooms.domain.usecase.GetLocalClientesPageUseCase
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesPageUseCase

class ClientesViewModelFactory(
    private val getClientesPageUseCase: GetClientesPageUseCase,
    private val getLocalClientesPageUseCase: GetLocalClientesPageUseCase,
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientesViewModel(
            getClientesPageUseCase = getClientesPageUseCase,
            getLocalClientesPageUseCase = getLocalClientesPageUseCase,
            connectivityMonitorRepository = connectivityMonitorRepository
        ) as T
    }
}
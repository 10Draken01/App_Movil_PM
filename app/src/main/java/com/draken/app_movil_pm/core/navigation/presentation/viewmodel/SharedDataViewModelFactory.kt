package com.draken.app_movil_pm.core.navigation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository

class SharedDataViewModelFactory(
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedDataViewModel(connectivityMonitorRepository) as T
    }
}
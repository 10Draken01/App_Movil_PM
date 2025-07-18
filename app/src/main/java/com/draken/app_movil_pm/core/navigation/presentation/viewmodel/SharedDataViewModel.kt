package com.draken.app_movil_pm.core.navigation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.navigation.data.NavigationRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SharedDataViewModel(
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
) : ViewModel() {
    private val _navController = MutableStateFlow<NavHostController?>(null)
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()
    private val _selectedCliente = MutableStateFlow<Cliente?>(null)
    val selectedCliente: StateFlow<Cliente?> = _selectedCliente.asStateFlow()
    private val _navigationRoutes = MutableStateFlow<NavigationRoutes>(NavigationRoutes)
    val navigationRoutes: StateFlow<NavigationRoutes> = _navigationRoutes.asStateFlow()

    init{
        // Estado inicial
        _isConnected.value = connectivityMonitorRepository.isCurrentlyConnected()
        viewModelScope.launch {
            connectivityMonitorRepository.observeConnectivity().collect { isConnected ->
                _isConnected.value = isConnected
                if (isConnected) {
                    _navController.value?.navigate(NavigationRoutes.LOGIN)
                } else {
                    _navController.value?.navigate(NavigationRoutes.CLIENTES)
                }
            }
        }
    }
    fun setSelectedCliente(cliente: Cliente) {
        _selectedCliente.value = cliente
    }

    fun clearSelectedCliente() {
        _selectedCliente.value = null
    }

    fun setNavController(navController: NavHostController){
        _navController.value = navController
    }
}
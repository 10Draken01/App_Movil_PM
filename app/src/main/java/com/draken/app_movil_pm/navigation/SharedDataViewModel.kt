// SharedDataViewModel.kt
package com.draken.app_movil_pm.navigation

import androidx.lifecycle.ViewModel
import com.draken.app_movil_pm.features.clientes.domain.model.Cliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedDataViewModel : ViewModel() {
    private val _selectedCliente = MutableStateFlow<Cliente?>(null)
    val selectedCliente: StateFlow<Cliente?> = _selectedCliente.asStateFlow()

    fun setSelectedCliente(cliente: Cliente) {
        _selectedCliente.value = cliente
    }

    fun clearSelectedCliente() {
        _selectedCliente.value = null
    }
}
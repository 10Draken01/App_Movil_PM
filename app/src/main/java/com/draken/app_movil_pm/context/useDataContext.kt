package com.draken.app_movil_pm.context

import com.draken.app_movil_pm.features.clientes.domain.model.Cliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UseDataContext {
    private val _cliente = MutableStateFlow<Cliente?>(null)
    val cliente: StateFlow<Cliente?> = _cliente

    fun setCliente(newCliente: Cliente?) {
        _cliente.value = newCliente
    }

    fun getCliente(): Cliente? {
        return _cliente.value
    }

    fun clearCliente() {
        _cliente.value = null
    }
}

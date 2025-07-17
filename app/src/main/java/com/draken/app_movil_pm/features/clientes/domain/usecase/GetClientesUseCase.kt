package com.draken.app_movil_pm.features.clientes.domain.usecase

import android.util.Log
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository

class GetClientesPageUseCase (private val repository: ClienteRepository) {
    suspend operator fun invoke(page: Int): List<Cliente> {
        val res = repository.getClientes(page)
        var clientes: List<Cliente> = emptyList()
        res.onSuccess {
            data ->
            clientes = data.data.clientes.map { it.toDomain() }
        }
        res.onFailure {
            err ->
            Log.e("getClientes", "Error: ${err.message}")
        }
        return clientes
    }
}
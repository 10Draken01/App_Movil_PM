package com.draken.app_movil_pm.features.clientes.domain.repository

import com.draken.app_movil_pm.features.clientes.domain.model.Cliente

interface ClienteRepository {
    suspend fun getClientes(page: Int): List<Cliente>
}
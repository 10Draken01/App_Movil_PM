package com.draken.app_movil_pm.features.clientes.domain.repository

import com.draken.app_movil_pm.features.clientes.data.model.ClienteResponseDto

interface ClienteRepository {
    suspend fun getClientes(page: Int): Result<ClienteResponseDto>
}
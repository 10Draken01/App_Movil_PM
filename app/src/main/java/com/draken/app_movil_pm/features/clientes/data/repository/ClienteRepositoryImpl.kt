package com.draken.app_movil_pm.features.clientes.data.repository

import com.draken.app_movil_pm.features.clientes.data.datasource.remote.ClienteService
import com.draken.app_movil_pm.features.clientes.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository

class ClienteRepositoryImpl(private val api: ClienteService) : ClienteRepository {
    override suspend fun getClientes(page: Int): List<Cliente> {
        return try {
            val response = api.getClientes(page)
            if (response.isSuccessful) {
                response.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

package com.draken.app_movil_pm.core.rooms.data.repository

import com.draken.app_movil_pm.core.rooms.data.service.ClientesAppDatabase
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import com.draken.app_movil_pm.core.rooms.domain.repository.ClienteDBRepository
import kotlinx.coroutines.flow.Flow

class ClienteDBRepositoryImpl(
    private val database: ClientesAppDatabase
): ClienteDBRepository {
    override fun getClientesPage(limit: Int, offset: Int): Flow<List<ClienteEntitie>> {
        return database
            .clienteDao()
            .getClientesPage(
                limit = limit,
                offset = offset
            )
    }

    override suspend fun getAllClientes(): Flow<List<ClienteEntitie>> {
        return database
            .clienteDao()
            .getAllClientes()
    }

    override suspend fun getClienteByClaveCliente(claveCliente: String): ClienteEntitie? {
        return database
            .clienteDao()
            .getClienteByClaveCliente(
            claveCliente = claveCliente
            )
    }

    override suspend fun insertCliente(cliente: ClienteEntitie): Boolean {
        val response = database
            .clienteDao()
            .insertCliente(
                cliente = cliente
            )
        return response > 0
    }

    override suspend fun updateCliente(cliente: ClienteEntitie): Boolean {
        val response = database
            .clienteDao()
            .updateCliente(
                claveCliente = cliente.claveCliente,
                nombre = cliente.nombre,
                celular = cliente.celular,
                email = cliente.email,
                characterIcon = cliente.characterIcon
            )
        return response > 0
    }

    override suspend fun deleteClienteByCliente(claveCliente: String): Boolean {
        val response = database
            .clienteDao()
            .deleteClienteByClaveCliente(
                claveCliente = claveCliente
            )
        return response > 0
    }

    override suspend fun getClienteCount(): Int {
        return database
            .clienteDao()
            .getClienteCount()
    }
}
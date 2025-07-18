package com.draken.app_movil_pm.core.rooms.domain.repository

import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import kotlinx.coroutines.flow.Flow

interface ClienteDBRepository {
    fun getClientesPage(limit: Int, offset: Int): Flow<List<ClienteEntitie>>

    suspend fun getClienteByClaveCliente(claveCliente: String): ClienteEntitie?

    suspend fun insertCliente(cliente: ClienteEntitie): Boolean

    suspend fun updateCliente(cliente: ClienteEntitie): Boolean

    suspend fun deleteClienteByCliente(claveCliente: String): Boolean

    suspend fun getClienteCount(): Int
}
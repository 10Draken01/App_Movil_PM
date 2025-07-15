package com.draken.app_movil_pm.core.rooms.domain.usecase

import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetClientesPageUseCase(
    private val repositoryFunGetClientesPage: suspend (limit: Int, offset: Int) -> Flow<List<ClienteEntitie>>
) {
    suspend operator fun invoke(page: Int): Flow<List<ClienteEntitie>> {
        val limit = 100
        val offset = (page - 1) * limit

        return repositoryFunGetClientesPage(limit, offset)
    }
}
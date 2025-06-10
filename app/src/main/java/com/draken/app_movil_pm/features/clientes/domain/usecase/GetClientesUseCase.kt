package com.draken.app_movil_pm.features.clientes.domain.usecase

import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository

class GetClientesUseCase (private val repository: ClienteRepository) {
    suspend operator fun invoke(page: Int) = repository.getClientes(page)
}
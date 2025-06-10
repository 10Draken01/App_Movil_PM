package com.draken.app_movil_pm.features.eliminar_cliente.domain.usecase

import com.draken.app_movil_pm.features.eliminar_cliente.domain.repository.EliminarClienteRepository

class EliminarClienteUseCase(private val repository: EliminarClienteRepository) {
    suspend operator fun invoke( clave_cliente: String, nombre: String) = repository.eliminar(clave_cliente, nombre)
}
package com.draken.app_movil_pm.features.editar_cliente.domain.usecase

import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository

class EditarClienteUseCase(private val repository: EditarClienteRepository) {
    suspend operator fun invoke(
        clave_cliente: String,
        nombre: String,
        celular: String,
        email: String,
        character_icon: Int
    ) = repository.editar(
        clave_cliente,
        nombre,
        celular,
        email,
        character_icon
    )
}
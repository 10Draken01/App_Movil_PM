package com.draken.app_movil_pm.features.agregar_cliente.domain.usecase

import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Character_Icon
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository

class AgregarClienteUseCase(private val repository: AgregarClienteRepository) {
    suspend operator fun invoke(
        clave_cliente: String,
        nombre: String,
        celular: String,
        email: String,
        character_icon: Character_Icon
    ): Response {
        return repository.agregar(
            clave_cliente,
            nombre,
            celular,
            email,
            character_icon.character_icon_uri ?: character_icon.character_icon_number
        )
    }
}
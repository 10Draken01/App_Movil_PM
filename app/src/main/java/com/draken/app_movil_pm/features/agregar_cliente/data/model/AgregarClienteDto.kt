package com.draken.app_movil_pm.features.agregar_cliente.data.model

import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Character_Icon


data class AgregarClienteDto(
    val clave_cliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val character_icon: Any?
)

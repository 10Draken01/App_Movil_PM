package com.draken.app_movil_pm.features.agregar_cliente.data.model


data class AgregarClienteDto(
    val clave_cliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val character_icon: Int
)

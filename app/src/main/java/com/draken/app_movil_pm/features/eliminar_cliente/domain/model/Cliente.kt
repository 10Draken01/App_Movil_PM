package com.draken.app_movil_pm.features.eliminar_cliente.domain.model

data class Cliente(
    val clave_cliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val character_icon: Int
)

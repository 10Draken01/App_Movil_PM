package com.draken.app_movil_pm.features.clientes.domain.model

data class Cliente(
    val claveCliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val characterIcon: Int
)

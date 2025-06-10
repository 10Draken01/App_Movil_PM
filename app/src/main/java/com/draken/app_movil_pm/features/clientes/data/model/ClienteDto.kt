package com.draken.app_movil_pm.features.clientes.data.model

import com.draken.app_movil_pm.features.clientes.domain.model.Cliente

data class ClienteDto(
    val id: String,
    val clave_cliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val character_icon: Int
) {
    fun toDomain() = Cliente(clave_cliente, nombre, celular, email, character_icon)
}

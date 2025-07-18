package com.draken.app_movil_pm.core.domain.model

data class Cliente(
    val claveCliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val characterIcon: CharacterIcon,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val queryType: QueryType? = null,
    val isLocal: Boolean = false
)
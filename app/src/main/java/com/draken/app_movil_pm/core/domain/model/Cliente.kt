package com.draken.app_movil_pm.core.domain.model

import java.util.Date

data class Cliente(
    val claveCliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val characterIcon: CharacterIcon,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
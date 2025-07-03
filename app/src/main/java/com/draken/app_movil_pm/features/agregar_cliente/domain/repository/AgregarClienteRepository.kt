package com.draken.app_movil_pm.features.agregar_cliente.domain.repository

import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response

interface AgregarClienteRepository {
    suspend fun agregar(clave_cliente: String, nombre: String, celular: String, email: String, character_icon: Any?): Response
}
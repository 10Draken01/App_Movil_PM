package com.draken.app_movil_pm.features.editar_cliente.domain.repository

import com.draken.app_movil_pm.features.editar_cliente.domain.model.Response

interface EditarClienteRepository {
    suspend fun editar(clave_cliente: String, nombre: String, celular: String, email: String, character_icon: Int): Response
}
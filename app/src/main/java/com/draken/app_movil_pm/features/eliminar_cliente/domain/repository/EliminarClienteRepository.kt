package com.draken.app_movil_pm.features.eliminar_cliente.domain.repository

import com.draken.app_movil_pm.features.eliminar_cliente.domain.model.Response

interface EliminarClienteRepository {
    suspend fun eliminar(clave_cliente: String, nombre: String): Response
}
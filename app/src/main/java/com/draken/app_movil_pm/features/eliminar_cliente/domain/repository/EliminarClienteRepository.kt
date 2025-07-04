package com.draken.app_movil_pm.features.eliminar_cliente.domain.repository

import com.draken.app_movil_pm.features.eliminar_cliente.domain.model.Response

interface EliminarClienteRepository {
    suspend fun eliminarCliente(claveCliente: String ): Response
}
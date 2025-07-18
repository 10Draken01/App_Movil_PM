package com.draken.app_movil_pm.core.add_cliente_service.domain.repository

import com.draken.app_movil_pm.core.domain.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AddClienteRepository {
    suspend fun addCliente(
        claveCliente: RequestBody,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Response
}
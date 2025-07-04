package com.draken.app_movil_pm.features.editar_cliente.domain.repository

import com.draken.app_movil_pm.core.domain.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface EditarClienteRepository {
    suspend fun editarCliente(
        claveCliente: String,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Result<Response>
}
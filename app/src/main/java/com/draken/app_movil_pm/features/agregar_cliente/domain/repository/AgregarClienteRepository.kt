package com.draken.app_movil_pm.features.agregar_cliente.domain.repository

import com.draken.app_movil_pm.core.domain.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AgregarClienteRepository {
    suspend fun agregarCliente(
        claveCliente: RequestBody,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Response
}
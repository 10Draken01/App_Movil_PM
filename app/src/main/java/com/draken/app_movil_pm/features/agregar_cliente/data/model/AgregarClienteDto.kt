package com.draken.app_movil_pm.features.agregar_cliente.data.model

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part


data class AgregarClienteDto(
    @Part("claveCliente") val claveCliente: RequestBody,
    @Part("nombre") val nombre: RequestBody,
    @Part("celular") val celular: RequestBody,
    @Part("email") val email: RequestBody,
    @Part("characterIcon") val characterIcon: RequestBody?,
    @Part val image: MultipartBody.Part?
)

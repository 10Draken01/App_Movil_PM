package com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.agregar_cliente.data.model.AgregarClienteDto
import com.draken.app_movil_pm.features.agregar_cliente.data.model.ResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AgregarClienteService {
    @Multipart
    @POST("clientes")
    suspend fun agregarCliente(
        @Part("claveCliente") claveCliente: RequestBody,
        @Part("nombre") nombre: RequestBody,
        @Part("celular") celular: RequestBody,
        @Part("email") email: RequestBody,
        @Part("characterIcon") characterIcon: RequestBody?,
        @Part image: MultipartBody.Part?
    ): Response<ResponseDto>
}

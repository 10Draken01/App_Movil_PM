package com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.editar_cliente.data.model.ResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EditarClienteService {
    @Multipart
    @PUT("clientes/{claveCliente}")
    suspend fun editarCliente(
        @Path("claveCliente") claveCliente : String,
        @Part("nombre") nombre: RequestBody,
        @Part("celular") celular: RequestBody,
        @Part("email") email: RequestBody,
        @Part("characterIcon") characterIcon: RequestBody?,
        @Part image: MultipartBody.Part?
    ): Response<ResponseDto>
}
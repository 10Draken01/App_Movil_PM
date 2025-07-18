package com.draken.app_movil_pm.core.add_cliente_service.data.datasource.remote

import com.draken.app_movil_pm.core.domain.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response as R
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AddClienteService {
    @Multipart
    @POST("clientes")
    suspend fun addCliente(
        @Part("claveCliente") claveCliente: RequestBody,
        @Part("nombre") nombre: RequestBody,
        @Part("celular") celular: RequestBody,
        @Part("email") email: RequestBody,
        @Part("characterIcon") characterIcon: RequestBody?,
        @Part image: MultipartBody.Part?
    ): R<Response>
}

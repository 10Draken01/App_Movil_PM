package com.draken.app_movil_pm.features.clientes.data.datasource.remote

import com.draken.app_movil_pm.features.clientes.data.model.ClienteDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClienteService {
    @GET("clientes/page/{page}")
    suspend fun getClientes(@Path("page") page: Int): Response<List<ClienteDto>>
}
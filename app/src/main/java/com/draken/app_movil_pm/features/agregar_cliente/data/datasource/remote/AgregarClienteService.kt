package com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.agregar_cliente.data.model.AgregarClienteDto
import com.draken.app_movil_pm.features.agregar_cliente.data.model.ResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AgregarClienteService {
    @POST("clientes")
    suspend fun agregar(@Body agregarClienteDto: AgregarClienteDto): Response<ResponseDto>
}
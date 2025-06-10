package com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.editar_cliente.data.model.ResponseDto
import com.draken.app_movil_pm.features.editar_cliente.data.model.EditarClienteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface EditarClienteService {
    @PUT("clientes/{clave_cliente}")
    suspend fun editar(@Path("clave_cliente") clave_cliente : String, @Body editarClienteDto: EditarClienteDto): Response<ResponseDto>
}
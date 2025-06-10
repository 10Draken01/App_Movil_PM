package com.draken.app_movil_pm.features.eliminar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.eliminar_cliente.data.model.ResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface EliminarClienteService {
    @DELETE("clientes/{clave_cliente}")
    suspend fun eliminar(@Path("clave_cliente") clave_cliente : String): Response<ResponseDto>
}
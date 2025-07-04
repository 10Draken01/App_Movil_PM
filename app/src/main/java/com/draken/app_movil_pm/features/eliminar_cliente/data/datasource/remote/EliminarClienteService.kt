package com.draken.app_movil_pm.features.eliminar_cliente.data.datasource.remote

import com.draken.app_movil_pm.features.eliminar_cliente.data.model.ResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface EliminarClienteService {
    @DELETE("clientes/{claveCliente}")
    suspend fun eliminarCliente(@Path("claveCliente") claveCliente : String): Response<ResponseDto>
}
package com.draken.app_movil_pm.features.eliminar_cliente.data.repository

import com.draken.app_movil_pm.features.eliminar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.eliminar_cliente.data.datasource.remote.EliminarClienteService
import com.draken.app_movil_pm.features.eliminar_cliente.domain.repository.EliminarClienteRepository
import java.io.IOException

class EliminarClienteRepositoryImpl(private val api: EliminarClienteService) : EliminarClienteRepository {
    override suspend fun eliminarCliente( claveCliente: String ): Response {
        return try {
            val res = api.eliminarCliente(claveCliente = claveCliente)

            if (res.isSuccessful && res.body() != null) {
                val responseDto = res.body()!!
                // Convertir DTO a modelo de dominio
                responseDto.toDomain()
            } else {
                // Manejo específico de códigos de error HTTP
                when (res.code()) {
                    401 -> Response( error = res.body()?.error, message = res.body()?.message)
                    400 -> Response( error = res.body()?.error, message = res.body()?.message)
                    404 -> Response( error = res.body()?.error, message = res.body()?.message)
                    500 -> Response( error = res.body()?.error, message = res.body()?.message)
                    else -> Response( error = res.body()?.error, message = res.body()?.message)
                }
            }
        } catch (e: IOException) {
            Response(error = "Error de red: ${e.localizedMessage}")
        } catch (e: Exception) {
            Response(error = "Excepción inesperada: ${e.localizedMessage}")
        }
    }
}
package com.draken.app_movil_pm.features.editar_cliente.data.repository

import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote.EditarClienteService
import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException

class EditarClienteRepositoryImpl(private val api: EditarClienteService) : EditarClienteRepository {
    override suspend fun editarCliente(
        claveCliente: String,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Result<Response> {
        return try {
            val res = api.editarCliente(
                claveCliente = claveCliente,
                nombre = nombre,
                celular = celular,
                email = email,
                characterIcon = characterIcon,
                image = image
            )

            if (res.isSuccessful && res.body() != null) {
                val responseDto = res.body()!!
                // Convertir DTO a modelo de dominio
                Result.success(responseDto.toDomain())
            } else {
                // Manejo específico de códigos de error HTTP
                val error = when (res.code()) {
                    401 -> "Error al editar $nombre"
                    400 -> "Error al editar $nombre"
                    404 -> "Cuenta todavia no esta registrada"
                    500 -> "Error interno del servidor"
                    else -> "Error en la petición: ${res.code()}"
                }
                Result.success(Response(error = error))
            }
        } catch (e: IOException) {
            Result.failure(Throwable(message = "Error de red: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Result.failure(Throwable(message = "Excepción inesperada: ${e.localizedMessage}"))

        }
    }
}
package com.draken.app_movil_pm.features.editar_cliente.data.repository

import com.draken.app_movil_pm.features.editar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote.EditarClienteService
import com.draken.app_movil_pm.features.editar_cliente.data.model.EditarClienteDto
import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository
import java.io.IOException

class EditarClienteRepositoryImpl(private val api: EditarClienteService) : EditarClienteRepository {
    override suspend fun editar(
        clave_cliente: String,
        nombre: String,
        celular: String,
        email: String,
        character_icon: Int
    ): Response {
        return try {
            val res = api.editar(
                clave_cliente = clave_cliente,
                editarClienteDto = EditarClienteDto(
                nombre,
                celular,
                email,
                character_icon
            ))

            if (res.isSuccessful && res.body() != null) {
                val responseDto = res.body()!!
                // Convertir DTO a modelo de dominio
                responseDto.toDomain()
            } else {
                // Manejo específico de códigos de error HTTP
                when (res.code()) {
                    401 -> Response( error = "Error al editar $nombre")
                    400 -> Response( error = "Error al editar $nombre")
                    404 -> Response( error = "Cuenta todavia no esta registrada")
                    500 -> Response( error = "Error interno del servidor")
                    else -> Response( error = "Error en la petición: ${res.code()}")
                }
            }
        } catch (e: IOException) {
            Response(error = "Error de red: ${e.localizedMessage}")
        } catch (e: Exception) {
            Response(error = "Excepción inesperada: ${e.localizedMessage}")
        }
    }
}
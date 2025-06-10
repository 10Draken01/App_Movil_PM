package com.draken.app_movil_pm.features.agregar_cliente.data.repository

import com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote.AgregarClienteService
import com.draken.app_movil_pm.features.agregar_cliente.data.model.AgregarClienteDto
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Cliente
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository
import java.io.IOException

class AgregarClienteRepositoryImpl(private val api: AgregarClienteService) : AgregarClienteRepository {
    override suspend fun agregar(
        clave_cliente: String,
        nombre: String,
        celular: String,
        email: String,
        character_icon: Int
    ): Response {
        return try {
            val res = api.agregar(agregarClienteDto = AgregarClienteDto(
                clave_cliente,
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
                    401 -> Response( error = "Error al agregar $nombre")
                    400 -> Response( error = "Error al agregar $nombre")
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
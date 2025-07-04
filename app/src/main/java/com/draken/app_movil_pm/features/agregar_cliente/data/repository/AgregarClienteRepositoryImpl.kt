package com.draken.app_movil_pm.features.agregar_cliente.data.repository

import android.util.Log
import com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote.AgregarClienteService
import com.draken.app_movil_pm.features.agregar_cliente.data.model.AgregarClienteDto
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException

class AgregarClienteRepositoryImpl(
    private val api: AgregarClienteService,
) : AgregarClienteRepository {
    override suspend fun agregarCliente(
        claveCliente: RequestBody,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Response {
        return try {
            val res = api.agregarCliente(
                claveCliente = claveCliente,
                nombre = nombre,
                celular = celular,
                email = email,
                characterIcon = characterIcon,
                image = image,
            )

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
            Log.e("AgregarCliente", "Error: ${e.localizedMessage}")
            Response(error = "Excepción inesperada: ${e.message}")
        }
    }
}
package com.draken.app_movil_pm.core.add_cliente_service.data.repository

import android.util.Log
import com.draken.app_movil_pm.core.add_cliente_service.data.datasource.remote.AddClienteService
import com.draken.app_movil_pm.core.add_cliente_service.domain.repository.AddClienteRepository
import com.draken.app_movil_pm.core.domain.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException

class AddClienteRepositoryImpl(
    private val service: AddClienteService,
) : AddClienteRepository {
    override suspend fun addCliente(
        claveCliente: RequestBody,
        nombre: RequestBody,
        celular: RequestBody,
        email: RequestBody,
        characterIcon: RequestBody?,
        image: MultipartBody.Part?
    ): Response {
        return try {
            val res = service.addCliente(
                claveCliente = claveCliente,
                nombre = nombre,
                celular = celular,
                email = email,
                characterIcon = characterIcon,
                image = image,
            )

            if (res.isSuccessful && res.body() != null) {
                val response = res.body()!!
                // Convertir DTO a modelo de dominio
                response
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
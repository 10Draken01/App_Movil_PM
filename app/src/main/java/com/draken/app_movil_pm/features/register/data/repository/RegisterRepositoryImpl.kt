package com.draken.app_movil_pm.features.register.data.repository

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.register.data.datasource.remote.RegisterService
import com.draken.app_movil_pm.features.register.data.model.RegisterRequestDto
import com.draken.app_movil_pm.features.register.domain.model.ResponseRegister
import com.draken.app_movil_pm.features.register.domain.repository.RegisterRepository
import java.io.IOException

class RegisterRepositoryImpl(private val api: RegisterService) : RegisterRepository {
    override suspend fun register(username: String, email: String, password: String): ResponseRegister {
        return try {
            val res = api.register(
                registerRequestDto = RegisterRequestDto(
                    username = username,
                    email = email,
                    password = password
                )
            )

            if (res.isSuccessful && res.body() != null) {
                val registerResponseDto = res.body()!!
                // Convertir DTO a modelo de dominio
                registerResponseDto.toDomain()
            } else {
                // Manejo específico de códigos de error HTTP
                when (res.code()) {
                    401 -> ResponseRegister( error = "Error al iniciar sesion")
                    400 -> ResponseRegister( error = "Error al iniciar sesion")
                    500 -> ResponseRegister( error = "Error interno del servidor")
                    else -> ResponseRegister( error = "Error en la petición: ${res.code()}")
                }
            }
        } catch (e: IOException) {
            ResponseRegister(error = "Error de red: ${e.localizedMessage}")
        } catch (e: Exception) {
            ResponseRegister(error = "Excepción inesperada: ${e.localizedMessage}")
        }
    }
}
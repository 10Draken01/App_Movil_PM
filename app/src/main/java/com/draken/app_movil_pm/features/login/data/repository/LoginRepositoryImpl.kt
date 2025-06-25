package com.draken.app_movil_pm.features.login.data.repository

import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.model.LoginDto
import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import java.io.IOException

class LoginRepositoryImpl(private val api: LoginService) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Login> {
        return try {
            val res = api.login(loginDto = LoginDto(email, password))

            if (res.isSuccessful && res.body() != null) {
                val loginResponseDto = res.body()!!
                // Convertir DTO a modelo de dominio

                Result.success(loginResponseDto.toDomain())
            } else {
                // Manejo específico de códigos de error HTTP
                when (res.code()) {
                    401 -> Result.success(Login( error = "Error al iniciar sesion"))
                    400 -> Result.success(Login( error = "Error al iniciar sesion"))
                    404 -> Result.success(Login( error = "Cuenta todavia no esta registrada"))
                    500 -> Result.success(Login( error = "Error interno del servidor"))
                    else -> Result.success(Login( error = "Error en la petición: ${res.code()}"))
                }
            }
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
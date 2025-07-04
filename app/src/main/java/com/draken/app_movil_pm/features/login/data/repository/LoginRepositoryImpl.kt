package com.draken.app_movil_pm.features.login.data.repository

import android.util.Log
import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.model.LoginDto
import com.draken.app_movil_pm.features.login.data.model.LoginResponseDto
import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.login.domain.repository.TokenRepository
import java.io.IOException

class LoginRepositoryImpl(
    private val api: LoginService
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Login> {
        return try {
            val res = api.login(loginDto = LoginDto(email, password))
            val body = res.body()

            // Verificar que la respuesta tenga body - CORREGIDO: Agregar return
            if (body == null) {
                return Result.failure(Exception("Respuesta inesperada del servidor"))
            }

            if (res.isSuccessful) {
                val authHeader = res.headers()["Authorization"]
                val token = authHeader?.removePrefix("Bearer ")?.trim()

                // Verificar que el token no sea nulo o vacío
                if (token.isNullOrEmpty()) {
                    return Result.failure(Exception("Token no encontrado en la respuesta"))
                }

                // Convertir DTO a modelo de dominio y retornar éxito
                Result.success(
                    Login(
                        message = body.message,
                        token = token, // Usar el token del header
                        error = null
                    )
                )
            } else {
                // Manejo específico de códigos de error HTTP - CORREGIDO: Null safety
                val errorMessage = when (res.code()) {
                    401 -> "Error al iniciar sesión"
                    400 -> "Error al iniciar sesión"
                    404 -> body.error ?: "Recurso no encontrado"
                    500 -> body.error ?: "Error interno del servidor"
                    else -> "Error en la petición: ${res.code()}"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Error de red: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Result.failure(Exception("Excepción inesperada: ${e.localizedMessage}"))
        }
    }
}
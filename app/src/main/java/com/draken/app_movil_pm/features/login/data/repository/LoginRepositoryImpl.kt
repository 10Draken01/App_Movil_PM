package com.draken.app_movil_pm.features.login.data.repository

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.model.LoginDto
import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import java.io.IOException

class LoginRepositoryImpl(private val api: LoginService) : LoginRepository {
    override suspend fun login(email: String, password: String): Login {
        return try {
            val res = api.login(loginDto = LoginDto(email, password))

            if (res.isSuccessful && res.body() != null) {
                val loginResponseDto = res.body()!!
                // Guardar el token usando el DTO
                RetrofitHelper.setToken(loginResponseDto.token)
                // Convertir DTO a modelo de dominio
                loginResponseDto.toDomain()
            } else {
                // Manejo específico de códigos de error HTTP
                when (res.code()) {
                    401 -> Login( error = "Error al iniciar sesion")
                    400 -> Login( error = "Error al iniciar sesion")
                    404 -> Login( error = "Cuenta todavia no esta registrada")
                    500 -> Login( error = "Error interno del servidor")
                    else -> Login( error = "Error en la petición: ${res.code()}")
                }
            }
        } catch (e: IOException) {
            Login(error = "Error de red: ${e.localizedMessage}")
        } catch (e: Exception) {
            Login(error = "Excepción inesperada: ${e.localizedMessage}")
        }
    }
}
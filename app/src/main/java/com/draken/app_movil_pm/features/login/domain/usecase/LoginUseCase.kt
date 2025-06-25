package com.draken.app_movil_pm.features.login.domain.usecase

import android.util.Log
import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.core.repository.TokenRepository

class LoginUseCase(
    private val repository: LoginRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(email: String, password: String): Result<Login> {

        val result = repository.login(email, password)
        Log.d("TOKEN", "Todo Ok")
        result.onSuccess {
                data -> tokenRepository.saveToken("Barier 232323") // Acá va p.e. data.token
        }.onFailure {
                exception -> tokenRepository.saveToken("")
        }
        // En caso de existir acá debe estar la lógica de negocio
        return result
    }
}
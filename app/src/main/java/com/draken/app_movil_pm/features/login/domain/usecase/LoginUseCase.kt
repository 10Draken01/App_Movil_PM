package com.draken.app_movil_pm.features.login.domain.usecase

import com.draken.app_movil_pm.features.login.domain.model.Login
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.login.domain.repository.TokenRepository

class LoginUseCase(
    private val repository: LoginRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(email: String, password: String) : Login {
        val response = repository.login(email, password)
        var data = Login()
        response.onSuccess {
            data ->
            tokenRepository.saveToken(data.token!!)
            return data
        }.onFailure {
            data ->
            return Login(error = data.message)
        }

        return data
    }
}
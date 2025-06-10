package com.draken.app_movil_pm.features.login.domain.usecase

import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}
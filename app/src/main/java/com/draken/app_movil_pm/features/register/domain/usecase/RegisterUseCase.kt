package com.draken.app_movil_pm.features.register.domain.usecase

import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.register.domain.repository.RegisterRepository

class RegisterUseCase(private val repository: RegisterRepository) {
    suspend operator fun invoke(username: String, email: String, password: String) = repository.register(username, email, password)
}
package com.draken.app_movil_pm.features.register.domain.repository

import com.draken.app_movil_pm.features.register.domain.model.ResponseRegister

interface RegisterRepository {
    suspend fun register(username: String, email: String, password: String): ResponseRegister
}
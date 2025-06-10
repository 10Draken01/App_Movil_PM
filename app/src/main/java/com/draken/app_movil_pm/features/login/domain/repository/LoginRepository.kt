package com.draken.app_movil_pm.features.login.domain.repository

import com.draken.app_movil_pm.features.login.domain.model.Login
import retrofit2.Response

interface LoginRepository {
    suspend fun login(email: String, password: String): Login
}
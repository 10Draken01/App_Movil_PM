package com.draken.app_movil_pm.features.login.domain.repository


interface TokenRepository {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
}
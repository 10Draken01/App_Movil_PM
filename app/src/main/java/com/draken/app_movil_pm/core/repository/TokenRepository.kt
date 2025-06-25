package com.draken.app_movil_pm.core.repository


interface TokenRepository {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
}
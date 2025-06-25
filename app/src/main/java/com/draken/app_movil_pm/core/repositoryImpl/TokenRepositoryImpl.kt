package com.draken.app_movil_pm.core.repositoryImpl

import com.draken.app_movil_pm.core.store.local.DataStoreManager
import com.draken.app_movil_pm.core.store.local.PreferenceKeys
import com.draken.app_movil_pm.core.repository.TokenRepository

class TokenRepositoryImpl(
    private val dataStore: DataStoreManager
) : TokenRepository {

    override suspend fun getToken(): String? = dataStore.getKey(PreferenceKeys.TOKEN)

    override suspend fun saveToken(token: String) = dataStore.saveKey(PreferenceKeys.TOKEN, token)
}
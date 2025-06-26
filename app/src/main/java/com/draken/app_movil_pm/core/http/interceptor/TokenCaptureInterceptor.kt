package com.draken.app_movil_pm.core.http.interceptor

import com.draken.app_movil_pm.core.store.local.DataStoreManager
import com.draken.app_movil_pm.core.store.local.PreferenceKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class TokenCaptureInterceptor(
    private val dataStore: DataStoreManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Extrae el token de la cabecera "Authorization" si está presente
        val authHeader = response.header("Authorization")

        if (!authHeader.isNullOrEmpty()) {
            // Guardar el token en DataStore de forma segura
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.saveKey(PreferenceKeys.TOKEN, authHeader)
            }
        }

        return response
    }
}
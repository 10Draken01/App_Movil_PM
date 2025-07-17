package com.draken.app_movil_pm.core.http.interceptor

import android.util.Log
import com.draken.app_movil_pm.core.data_store.DataStoreManager
import com.draken.app_movil_pm.core.data_store.PreferenceKeys
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AddTokenInterceptor(
    private val dataStore: DataStoreManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking {
            try {
                dataStore.getKey(PreferenceKeys.TOKEN)
            } catch (e: Exception) {
                null
            }
        }

        token?.let {
            Log.d("AuthInterceptor", "Token: $it")
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}
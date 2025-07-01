package com.draken.app_movil_pm.core.http

import com.draken.app_movil_pm.core.di.DataStoreModule
import com.draken.app_movil_pm.core.http.interceptor.AddTokenInterceptor
import com.draken.app_movil_pm.core.http.interceptor.TokenCaptureInterceptor
import com.draken.app_movil_pm.core.http.interceptor.provideLoggingInterceptor
import com.draken.app_movil_pm.core.store.local.DataStoreManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val BASE_URL = "http://172.18.192.1:8000/api/"
    private const val TIMEOUT = 20L

    private var retrofit: Retrofit? = null

    fun init(extraInterceptors: List<Interceptor> = emptyList()) {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    retrofit = buildRetrofit(extraInterceptors)
                }
            }
        }
    }

    fun <T> getService(serviceClass: Class<T>): T {
        requireNotNull(retrofit) { "RetrofitClient no ha sido inicializado. Llama a init() primero." }
        return retrofit!!.create(serviceClass)
    }

    private fun buildRetrofit(extraInterceptors: List<Interceptor>): Retrofit {
        val client = buildHttpClient(extraInterceptors)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildHttpClient(extraInterceptors: List<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(AddTokenInterceptor(requireNotNull(DataStoreModule.dataStoreManager)))
            .addInterceptor(TokenCaptureInterceptor(requireNotNull(DataStoreModule.dataStoreManager)))
            .addInterceptor(provideLoggingInterceptor())
            .apply {
                extraInterceptors.forEach { addInterceptor(it) }
            }
            .build()
    }
}

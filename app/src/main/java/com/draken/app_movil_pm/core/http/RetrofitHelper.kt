package com.draken.app_movil_pm.core.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "http://192.168.1.82:8000/api/"
    private var authToken: String? = null

    // Función para establecer el token
    fun setToken(token: String) {
        authToken = token
        rebuildRetrofitInstance()
    }

    // Retrofit actualizable
    private var _retrofitInstance: Retrofit? = null
    val retrofitInstance: Retrofit
        get() = _retrofitInstance ?: buildRetrofit().also { _retrofitInstance = it }

    private fun buildRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()

        // Agrega el interceptor solo si hay token
        authToken?.let { token ->
            val interceptor = Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }
            clientBuilder.addInterceptor(interceptor)
        }

        val client = clientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun rebuildRetrofitInstance() {
        _retrofitInstance = buildRetrofit()
    }
}

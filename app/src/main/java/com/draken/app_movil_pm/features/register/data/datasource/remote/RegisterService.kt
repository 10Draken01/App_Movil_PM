package com.draken.app_movil_pm.features.register.data.datasource.remote

import com.draken.app_movil_pm.features.register.data.model.RegisterRequestDto
import com.draken.app_movil_pm.features.register.data.model.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("users/register")
    suspend fun register(@Body registerRequestDto:RegisterRequestDto ): Response<RegisterResponseDto>
}
package com.draken.app_movil_pm.features.login.data.datasource.remote

import com.draken.app_movil_pm.features.login.data.model.LoginDto
import com.draken.app_movil_pm.features.login.data.model.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("users/login")
    suspend fun login(@Body loginDto: LoginDto): Response<LoginResponseDto>
}
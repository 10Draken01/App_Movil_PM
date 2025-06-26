package com.draken.app_movil_pm.features.register.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.register.data.datasource.remote.RegisterService
import com.draken.app_movil_pm.features.register.data.repository.RegisterRepositoryImpl
import com.draken.app_movil_pm.features.register.domain.repository.RegisterRepository
import com.draken.app_movil_pm.features.register.domain.usecase.RegisterUseCase

object AppModule {

    // Register
    private val registerService: RegisterService by lazy {
        RetrofitHelper.getService(RegisterService::class.java)
    }

    private val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl(registerService)
    }

    val registerUseCase: RegisterUseCase by lazy {
        RegisterUseCase(registerRepository)
    }
}
package com.draken.app_movil_pm.features.register.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.register.data.datasource.remote.RegisterService
import com.draken.app_movil_pm.features.register.data.repository.RegisterRepositoryImpl
import com.draken.app_movil_pm.features.register.domain.repository.RegisterRepository
import com.draken.app_movil_pm.features.register.domain.usecase.RegisterUseCase

object AppModule {
    private val api: RegisterService = RetrofitHelper.retrofitInstance.create(RegisterService::class.java)

    private val repository: RegisterRepository = RegisterRepositoryImpl(api)
    val registerUseCase = RegisterUseCase (repository)
}
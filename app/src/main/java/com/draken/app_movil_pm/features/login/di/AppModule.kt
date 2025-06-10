package com.draken.app_movil_pm.features.login.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.repository.LoginRepositoryImpl
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase

object AppModule {
    private val api: LoginService = RetrofitHelper.retrofitInstance.create(LoginService::class.java)

    private val repository: LoginRepository = LoginRepositoryImpl(api)
    val loginUseCase = LoginUseCase (repository)
}
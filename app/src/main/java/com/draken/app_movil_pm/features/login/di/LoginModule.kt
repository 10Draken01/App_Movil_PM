package com.draken.app_movil_pm.features.login.di

import com.draken.app_movil_pm.core.di.DataStoreModule
import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.core.datastore.DataStoreManager
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.repository.LoginRepositoryImpl
import com.draken.app_movil_pm.features.login.data.repository.TokenRepositoryImpl
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.login.domain.repository.TokenRepository
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase

object LoginModule {
    init {
        RetrofitHelper.init()
    }

    private var dataStoreManager: DataStoreManager = DataStoreModule.dataStoreManager

    // Login
    private val tokenRepository: TokenRepository by lazy {
        TokenRepositoryImpl(dataStoreManager)
    }

    private val loginService: LoginService by lazy {
        RetrofitHelper.getService(LoginService::class.java)
    }

    private val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl(loginService)
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(loginRepository, tokenRepository)
    }
}
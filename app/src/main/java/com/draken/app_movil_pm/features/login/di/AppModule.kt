package com.draken.app_movil_pm.features.login.di

import android.content.Context
import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.core.store.local.DataStoreManager
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.repository.LoginRepositoryImpl
import com.draken.app_movil_pm.core.repositoryImpl.TokenRepositoryImpl
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.core.repository.TokenRepository
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase

object AppModule {

    private lateinit var appContext: Context
    private lateinit var dataStoreManager: DataStoreManager

    private var isInitialized = false

    fun init(context: Context) {
        if (!isInitialized) {
            appContext = context.applicationContext
            dataStoreManager = DataStoreManager(appContext)
            RetrofitHelper.init(dataStoreManager)
            isInitialized = true
        }
    }

    // Repository
    private val tokenRepository: TokenRepository by lazy {
        TokenRepositoryImpl(dataStoreManager)
    }

    private val loginService: LoginService by lazy {
        RetrofitHelper.getService(LoginService::class.java)
    }

    private val repositoryLogin: LoginRepository by lazy {
        LoginRepositoryImpl(loginService)
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(repositoryLogin, tokenRepository)
    }
}
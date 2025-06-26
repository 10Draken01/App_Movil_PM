package com.draken.app_movil_pm.core.di

import android.content.Context
import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.core.store.local.DataStoreManager
import com.draken.app_movil_pm.features.login.data.datasource.remote.LoginService
import com.draken.app_movil_pm.features.login.data.repository.LoginRepositoryImpl
import com.draken.app_movil_pm.features.login.data.repository.TokenRepositoryImpl
import com.draken.app_movil_pm.features.login.domain.repository.LoginRepository
import com.draken.app_movil_pm.features.login.domain.repository.TokenRepository
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase
import com.draken.app_movil_pm.features.register.data.datasource.remote.RegisterService
import com.draken.app_movil_pm.features.register.data.repository.RegisterRepositoryImpl
import com.draken.app_movil_pm.features.register.domain.repository.RegisterRepository
import com.draken.app_movil_pm.features.register.domain.usecase.RegisterUseCase

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
}
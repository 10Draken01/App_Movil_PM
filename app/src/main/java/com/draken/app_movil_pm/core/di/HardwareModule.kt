package com.draken.app_movil_pm.core.di

import android.content.Context
import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.hardware.data.CamaraManager
import com.draken.app_movil_pm.core.hardware.data.CamaraRepositoryImpl
import com.draken.app_movil_pm.core.hardware.domain.CamaraRepository

object HardwareModule {
    private var appContext: Context = AppContextHolder.get()

    private val camaraManager: CamaraManager by lazy {
        CamaraManager(appContext)
    }

    private val camaraRepository: CamaraRepository by lazy {
        CamaraRepositoryImpl(camaraManager)
    }

    fun provideCamaraRepository(): CamaraRepository = camaraRepository
}
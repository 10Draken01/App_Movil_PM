package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.hardware.data.CamaraManager
import com.draken.app_movil_pm.core.hardware.domain.CamaraRepository

object HardwareModule {
    val camaraRepository: CamaraRepository by lazy {
        CamaraManager(AppContextHolder.get())
    }
}
package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.hardware.data.CameraManager
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository

object HardwareModule {
    val camaraRepository: CameraManagerRepository by lazy {
        CameraManager(AppContextHolder.get())
    }
}
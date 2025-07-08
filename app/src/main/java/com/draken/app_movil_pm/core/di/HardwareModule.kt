package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.hardware.data.CameraManager
import com.draken.app_movil_pm.core.hardware.data.VibratorManager
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import com.draken.app_movil_pm.core.hardware.domain.VibratorRepository

object HardwareModule {
    val cameraRepository: CameraManagerRepository by lazy {
        CameraManager(context = AppContextHolder.get())
    }

    val vibratorRepository: VibratorRepository by lazy {
        VibratorManager(context = AppContextHolder.get())
    }
}
package com.draken.app_movil_pm.core.hardware.domain

interface VibratorRepository {
    fun vibrate()
    fun hasVibrator(): Boolean
}
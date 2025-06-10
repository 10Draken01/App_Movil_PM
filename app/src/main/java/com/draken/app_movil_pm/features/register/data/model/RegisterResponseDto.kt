package com.draken.app_movil_pm.features.register.data.model

import com.draken.app_movil_pm.features.register.domain.model.ResponseRegister

data class RegisterResponseDto(
    val message: String? = null,
    val error: String? = null
){
    fun toDomain() = ResponseRegister(message, error)
}

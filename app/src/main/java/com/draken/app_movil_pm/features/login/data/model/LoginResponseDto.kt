package com.draken.app_movil_pm.features.login.data.model

import com.draken.app_movil_pm.features.login.domain.model.Login

data class LoginResponseDto(
    val message: String,
    val token: String
){
    fun toDomain() = Login(message, token)
}

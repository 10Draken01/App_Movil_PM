package com.draken.app_movil_pm.features.agregar_cliente.data.model

import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response

data class ResponseDto(
    val message: String? = null,
    val error: String? = null
){
    fun toDomain() = Response(message, error)
}

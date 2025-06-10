package com.draken.app_movil_pm.features.eliminar_cliente.domain.model


data class InputType(
    val label: String,
    val placeholder: String,
    val value: String,
    val onChange: (it: String) -> Unit
)

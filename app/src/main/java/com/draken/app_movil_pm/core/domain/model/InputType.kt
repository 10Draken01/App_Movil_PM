package com.draken.app_movil_pm.core.domain.model


data class InputType(
    val label: String,
    val placeholder: String,
    val value: String,
    val onChange: (it: String) -> Unit
)

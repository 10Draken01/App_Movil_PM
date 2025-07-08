package com.draken.app_movil_pm.core.domain.model

data class SimpleImagePickerActions(
    val state: SimpleImagePickerState,
    val pickImage: () -> Unit,
    val clearImage: () -> Unit,
    val clearError: () -> Unit
)

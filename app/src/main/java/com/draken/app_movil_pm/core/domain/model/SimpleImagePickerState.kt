package com.draken.app_movil_pm.core.domain.model

import android.net.Uri

data class SimpleImagePickerState(
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

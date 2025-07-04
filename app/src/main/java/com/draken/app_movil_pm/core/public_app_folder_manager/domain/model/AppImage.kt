package com.draken.app_movil_pm.core.public_app_folder_manager.domain.model

import android.net.Uri

data class AppImage(
    val uri: Uri,
    val name: String,
    val path: String,
    val size: Long,
    val dateModified: Long
)

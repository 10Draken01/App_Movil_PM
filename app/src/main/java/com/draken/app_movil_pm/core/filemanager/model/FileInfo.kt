package com.draken.app_movil_pm.core.filemanager.model

data class FileInfo(
    val fileName: String,
    val size: Long,
    val mimeType: String,
    val extension: String
)

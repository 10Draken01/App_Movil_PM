package com.draken.app_movil_pm.core.filemanager.domain

import android.net.Uri
import com.draken.app_movil_pm.core.filemanager.model.FileInfo
import com.draken.app_movil_pm.core.filemanager.model.ImageDimensions
import com.draken.app_movil_pm.core.filemanager.model.ValidationResult
import okhttp3.MultipartBody

interface FileManagerRepository {
    fun uriToMultipartBody(uri: Uri, partName: String): MultipartBody.Part?
    fun hasRequiredPermissions(): Boolean
    fun validateImageUri(uri: Uri): ValidationResult
    fun getFileInfoFromUri(uri: Uri): FileInfo
    fun cleanupTempFiles()
    fun getImageDimensions(uri: Uri): ImageDimensions?
    fun isUriAccessible(uri: Uri): Boolean
    fun formatFileSize(bytes: Long): String
}
package com.draken.app_movil_pm.core.filemanager.domain

import android.net.Uri
import okhttp3.MultipartBody

interface FileManagerRepository {
    fun uriToMultipartBody(uri: Uri, partName: String): MultipartBody.Part?
}
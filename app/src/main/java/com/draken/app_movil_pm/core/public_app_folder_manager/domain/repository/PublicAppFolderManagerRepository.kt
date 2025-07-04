package com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository

import android.net.Uri
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.model.AppImage

interface PublicAppFolderManagerRepository {
    fun createAppFolderUri( fileName: String, subfolder: String? ): Uri?
    fun finalizeImage(uri: Uri): Boolean
    suspend fun getAppImages(): List<AppImage>
    suspend fun deleteAppImage(uri: Uri): Boolean
    fun getAppFolderPath(): String
}
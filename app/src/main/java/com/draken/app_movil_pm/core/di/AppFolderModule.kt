package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.public_app_folder_manager.data.PublicAppFolderManager
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository

object AppFolderModule {
    val publicAppFolderManagerRepository: PublicAppFolderManagerRepository by lazy {
        PublicAppFolderManager(AppContextHolder.get())
    }
}
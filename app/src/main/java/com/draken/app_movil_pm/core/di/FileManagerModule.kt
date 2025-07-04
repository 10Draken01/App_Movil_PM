package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.filemanager.data.FileManager
import com.draken.app_movil_pm.core.filemanager.domain.FileManagerRepository

object FileManagerModule {
    val fileManagerRepository: FileManagerRepository by lazy {
        FileManager(AppContextHolder.get())
    }
}
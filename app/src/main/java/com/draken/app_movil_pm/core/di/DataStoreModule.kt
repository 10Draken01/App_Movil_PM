package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.app_context.AppContextHolder
import com.draken.app_movil_pm.core.data_store.DataStoreManager

object DataStoreModule {
    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(AppContextHolder.get())
    }
}
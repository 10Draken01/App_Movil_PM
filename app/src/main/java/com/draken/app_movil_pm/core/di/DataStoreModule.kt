package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.datastore.DataStoreManager

object DataStoreModule {
    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(AppContextHolder.get())
    }
}
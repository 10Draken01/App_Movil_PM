package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.app_context.AppContextHolder
import com.draken.app_movil_pm.core.connectivity_monitor.data.ConnectivityMonitor
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository


object ConnectivityMonitorModule {
    val connectivityMonitorRepository: ConnectivityMonitorRepository by lazy {
        ConnectivityMonitor(AppContextHolder.get())
    }
}
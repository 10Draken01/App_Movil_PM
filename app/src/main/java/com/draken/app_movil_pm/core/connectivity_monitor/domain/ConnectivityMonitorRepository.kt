package com.draken.app_movil_pm.core.connectivity_monitor.domain

import kotlinx.coroutines.flow.Flow

interface ConnectivityMonitorRepository {
    fun observeConnectivity(): Flow<Boolean>
    fun isCurrentlyConnected(): Boolean
}
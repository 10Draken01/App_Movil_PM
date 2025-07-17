package com.draken.app_movil_pm.core.connectivity_monitor.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class ConnectivityMonitor(private val context: Context): ConnectivityMonitorRepository {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observeConnectivity(): Flow<Boolean> = callbackFlow {
        // Callback que recibe eventos de red
        val callback = object : ConnectivityManager.NetworkCallback() {

            // Se ejecuta cuando una red se vuelve disponible
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true) // Envía true al Flow
            }

            // Se ejecuta cuando se pierde una red
            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false) // Envía false al Flow
            }

            // Se ejecuta cuando cambian las capacidades de la red
            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                // Verifica si tiene internet real (no solo conexión)
                val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                trySend(hasInternet)
            }
        }

        // Configuración de qué tipo de redes observar
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        // Registra el callback
        connectivityManager.registerNetworkCallback(request, callback)

        // Envía el estado inicial
        trySend(isCurrentlyConnected())

        // Limpieza cuando se cancela el Flow
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged() // Solo emite cuando el valor cambia

    // Metodo para verificar el estado actual
    override fun isCurrentlyConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
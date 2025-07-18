// 1. ConnectivityService.kt - CORREGIDO
package com.draken.app_movil_pm.core.connectivity_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.core.connectivity_monitor.data.ConnectivityMonitor
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.add_cliente_service.di.AddClienteModule
import com.draken.app_movil_pm.core.di.LocalClientesModule
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.QueryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ConnectivityService : Service() {

    companion object {
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "connectivity_channel"
        private const val CHANNEL_NAME = "Estado de Conectividad"
    }

    // ✅ CORREGIDO: No inyectar dependencias en constructor de Service
    // Android crea los Services automáticamente
    private lateinit var connectivityMonitorRepository: ConnectivityMonitorRepository

    // Scope para coroutines del servicio
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // Estado actual de conexión
    private var isConnected = true

    override fun onCreate() {
        super.onCreate()
        connectivityMonitorRepository = ConnectivityMonitor(context = this)
        // O si tienes: ConnectivityMonitorImpl(MyApplication.instance)

        // Crear canal de notificaciones
        createNotificationChannel()

        // Iniciar como foreground service
        startForeground(NOTIFICATION_ID, createNotification(true))

        // Comenzar a monitorear
        monitorConnectivity()

        Log.d("ConnectivityService", "Servicio iniciado correctamente")
    }

    override fun onDestroy() {
        // Cancela todas las coroutines
        serviceScope.cancel()
        Log.d("ConnectivityService", "Servicio destruido")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // START_STICKY: El sistema reinicia el servicio si se mata
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun monitorConnectivity() {
        serviceScope.launch {
            try {
                connectivityMonitorRepository.observeConnectivity().collect { connected ->
                    Log.d("ConnectivityService", "Estado de conectividad: $connected")

                    // Solo actualizar si el estado cambió
                    if (isConnected != connected) {
                        isConnected = connected
                        updateNotification(connected)

                        // Lógica adicional según el estado
                        handleConnectivityChange(connected)
                    }
                }
            } catch (e: Exception) {
                Log.e("ConnectivityService", "Error en monitoreo de conectividad", e)
            }
        }
    }

    private fun handleConnectivityChange(connected: Boolean) {
        if (connected) {
            Log.i("ConnectivityService", "Conexión restaurada - iniciando sincronización")
            startDataSynchronization()
        } else {
            Log.i("ConnectivityService", "Conexión perdida - pausando sincronización")
            pauseDataSynchronization()
        }
    }

    private fun startDataSynchronization() {
        serviceScope.launch {
            try {
                Log.d("ConnectivityService", "Iniciando sincronización de datos...")
                val getAllLocalClientesPageUseCase = LocalClientesModule.getAllLocalClientesPageUseCase
                val addClienteUseCase = AddClienteModule.addClienteUseCase
                val deleteLocalClienteUseCase = LocalClientesModule.deleteLocalClienteUseCase
                getAllLocalClientesPageUseCase().collect {
                    responseRooms ->
                    if( responseRooms.data.isEmpty() ) {
                        Log.d("ConnectivityService", "No hay clientes locales para sincronizar")

                    } else {
                        Log.d("ConnectivityService", "Hay ${responseRooms.data.size} clientes locales para sincronizar")
                        val clientes = responseRooms.toDomain()
                        clientes.forEach { cliente ->
                            if( cliente.queryType == QueryType.ADD){
                                var response = addClienteUseCase(
                                    claveCliente = cliente.claveCliente,
                                    nombre = cliente.nombre,
                                    celular = cliente.celular,
                                    email = cliente.email,
                                    characterIcon = CharacterIcon(
                                        characterIconNumber = cliente.characterIcon.characterIconNumber,
                                        characterIconUrl = cliente.characterIcon.characterIconUrl,
                                        characterIconUri = cliente.characterIcon.characterIconUri
                                    )
                                )
                                Log.d("ConnectivityService", "Sincronización de cliente ${cliente.claveCliente} - ${response.message}")
                                response = deleteLocalClienteUseCase(claveCliente = cliente.claveCliente)
                                Log.d("ConnectivityService", "Eliminación de cliente ${cliente.claveCliente} - ${response.message}")

                            } else if( cliente.queryType == QueryType.UPDATE){

                            } else {
                                Log.d("ConnectivityService", "Cliente sin tipo de consulta: ${cliente.claveCliente}")
                            }
                        }
                    }
                }


            } catch (e: Exception) {
                Log.e("ConnectivityService", "Error en sincronización de datos", e)
            }
        }
    }

    private fun pauseDataSynchronization() {
        Log.d("ConnectivityService", "Pausando sincronización de datos...")
        // TODO: Implementar tu lógica para pausar sincronización
        // - Cancelar operaciones de red en curso
        // - Marcar datos como pendientes
        // - Habilitar modo offline
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW // Baja prioridad = no interrumpe
        ).apply {
            description = "Monitoreo del estado de conexión a internet para sincronización"
            setSound(null, null) // Sin sonido
            enableVibration(false) // Sin vibración
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }

    private fun createNotification(connected: Boolean): Notification {
        val title = if (connected) "✅ Conectado - Sincronizando" else "❌ Sin Conexión - Datos Pendientes"
        val text = if (connected) "Sincronización de datos activa" else "Esperando conexión para sincronizar"

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(
                R.drawable.logo
            )
            .setColorized(true)
            .setColor(Notification.DEFAULT_ALL)
            .setOngoing(true) // No se puede deslizar para cerrar
            .setAutoCancel(false) // No se cierra al tocar
            .setPriority(NotificationCompat.PRIORITY_LOW) // Baja prioridad
            .build()
    }

    private fun updateNotification(connected: Boolean) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.notify(NOTIFICATION_ID, createNotification(connected))
    }
}
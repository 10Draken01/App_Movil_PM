package com.draken.app_movil_pm

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.draken.app_movil_pm.core.app_context.AppContextHolder
import com.draken.app_movil_pm.core.connectivity_service.ConnectivityService
import com.draken.app_movil_pm.core.navigation.presentation.view.AppNavigation
import com.draken.app_movil_pm.ui.theme.App_Movil_PMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContextHolder.init(this)
        startConnectivityService()
        // ViewModel compartido a nivel de navegación
        enableEdgeToEdge()
        setContent {
            App_Movil_PMTheme {
//                val sharedViewModel: SharedDataViewModel = viewModel()
//                sharedViewModel.setSelectedCliente(Cliente(
//                    claveCliente = "2",
//                    nombre = "a",
//                    celular = "1231231231",
//                    email = "lwad@asd.com",
//                    characterIcon = CharacterIcon()
//                ))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                    // AgregarClienteScreen()
                    // EditarClienteScreen(sharedViewModel)
                }
            }
        }
    }

    private fun startConnectivityService() {
        val intent = Intent(this, ConnectivityService::class.java)
        startForegroundService(intent)
    }

    override fun onDestroy() {
        // Opcional: Detener el servicio cuando se cierra la app principal
        // val intent = Intent(this, ConnectivityService::class.java)
        // stopService(intent)
        super.onDestroy()
    }
}

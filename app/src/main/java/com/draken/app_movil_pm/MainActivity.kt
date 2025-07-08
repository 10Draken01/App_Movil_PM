package com.draken.app_movil_pm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draken.app_movil_pm.core.appcontext.AppContextHolder
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.navigation.AppNavigation
import com.draken.app_movil_pm.core.navigation.SharedDataViewModel
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.AgregarClienteScreen
import com.draken.app_movil_pm.features.editar_cliente.presentation.view.EditarClienteScreen
import com.draken.app_movil_pm.ui.theme.App_Movil_PMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContextHolder.init(this)
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
}

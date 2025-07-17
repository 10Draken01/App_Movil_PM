package com.draken.app_movil_pm.core.navigation.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.draken.app_movil_pm.core.di.ConnectivityMonitorModule
import com.draken.app_movil_pm.core.navigation.data.NavigationRoutes
import com.draken.app_movil_pm.core.navigation.presentation.viewmodel.SharedDataViewModel
import com.draken.app_movil_pm.core.navigation.presentation.viewmodel.SharedDataViewModelFactory
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.AgregarClienteScreen
import com.draken.app_movil_pm.features.editar_cliente.presentation.view.EditarClienteScreen
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.EliminarClienteScreen
import com.draken.app_movil_pm.features.clientes.presentation.view.ClientesScreen
import com.draken.app_movil_pm.features.login.presentation.view.LoginScreen
import com.draken.app_movil_pm.features.register.presentation.view.RegisterScreen

@Composable
fun AppNavigation(
    sharedViewModel: SharedDataViewModel = viewModel(
        factory = SharedDataViewModelFactory(
            connectivityMonitorRepository = ConnectivityMonitorModule.connectivityMonitorRepository
        )
    )
) {
    val navController: NavHostController = rememberNavController()
    val isConnected by sharedViewModel.isConnected.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if(isConnected) NavigationRoutes.LOGIN else NavigationRoutes.CLIENTES
    ) {
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                },
                onNavigateToClientes = {
                    navController.navigate(NavigationRoutes.CLIENTES) {
                        // Limpia el stack de navegación para que no pueda volver atrás
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoutes.CLIENTES) {
            ClientesScreen(
                onNavigateToAgregarCliente = {
                    navController.navigate(NavigationRoutes.AGREGAR_CLIENTE)
                },
                onNavigateToEditarCliente = { cliente ->
                    // Establecer el cliente seleccionado en el ViewModel compartido
                    sharedViewModel.setSelectedCliente(cliente)
                    navController.navigate(NavigationRoutes.EDITAR_CLIENTE)
                },
                onNavigateToEliminarCliente = { cliente ->
                    // Establecer el cliente seleccionado en el ViewModel compartido
                    sharedViewModel.setSelectedCliente(cliente)
                    navController.navigate(NavigationRoutes.ELIMINAR_CLIENTE)
                }
            )
        }

        composable(NavigationRoutes.AGREGAR_CLIENTE) {
            AgregarClienteScreen(
                onNavigateToClientes = {
                    navController.navigate(NavigationRoutes.CLIENTES) {
                        // Opcional: limpiar el stack hasta clientes
                        popUpTo(NavigationRoutes.CLIENTES) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.EDITAR_CLIENTE) {
            EditarClienteScreen(
                sharedViewModel = sharedViewModel,
                onNavigateToClientes = {
                    navController.navigate(NavigationRoutes.CLIENTES) {
                        // Opcional: limpiar el stack hasta clientes
                        popUpTo(NavigationRoutes.CLIENTES) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.ELIMINAR_CLIENTE) {
            EliminarClienteScreen(
                sharedViewModel = sharedViewModel,
                onNavigateToClientes = {
                    navController.navigate(NavigationRoutes.CLIENTES) {
                        // Opcional: limpiar el stack hasta clientes
                        popUpTo(NavigationRoutes.CLIENTES) { inclusive = true }
                    }
                }
            )
        }
    }
}
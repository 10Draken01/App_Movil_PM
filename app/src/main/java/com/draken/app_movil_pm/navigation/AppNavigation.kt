package com.draken.app_movil_pm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.AgregarClienteScreen
import com.draken.app_movil_pm.features.editar_cliente.presentation.view.EditarClienteScreen
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.EliminarClienteScreen
import com.draken.app_movil_pm.features.login.presentation.view.ClientesScreen
import com.draken.app_movil_pm.features.login.presentation.view.LoginScreen
import com.draken.app_movil_pm.features.register.presentation.view.RegisterScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    // ViewModel compartido a nivel de navegación
    val sharedViewModel: SharedDataViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
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
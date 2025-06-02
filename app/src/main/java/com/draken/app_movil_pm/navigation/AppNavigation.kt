package com.draken.app_movil_pm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.draken.app_movil_pm.features.login.presentation.view.LoginScreen
import com.draken.app_movil_pm.features.register.presentation.view.RegisterScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                },
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.HOME) {
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
                },
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.HOME) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}
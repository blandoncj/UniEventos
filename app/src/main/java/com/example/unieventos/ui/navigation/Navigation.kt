package com.example.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unieventos.ui.screens.LoginScreen
import com.example.unieventos.ui.screens.customer.ChangePasswordScreen
import com.example.unieventos.ui.screens.customer.ConfirmAccountScreen
import com.example.unieventos.ui.screens.customer.RecoverPasswordScreen
import com.example.unieventos.ui.screens.customer.SignupScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToSignup = { navController.navigate(RouteScreen.Signup) },
                onNavigateToRecoverPassword = { navController.navigate(RouteScreen.RecoverPassword) }
            )
        }

        composable<RouteScreen.Signup> {
            SignupScreen(
                onNavigateToLogin = { navController.navigate(RouteScreen.Login) },
                onNavigateToConfirmAccount = { navController.navigate(RouteScreen.ConfirmAccount) }
            )
        }

        composable<RouteScreen.ConfirmAccount> {
            ConfirmAccountScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.RecoverPassword> {
            RecoverPasswordScreen(
                onNavigateToLogin = { navController.navigate(RouteScreen.Login) },
                onNavigateToChangePassword = { navController.navigate(RouteScreen.ChangePassword) }
            )
        }

        composable<RouteScreen.ChangePassword> {
            ChangePasswordScreen(
                onNavigateToRecoverPassword = { navController.navigate(RouteScreen.RecoverPassword) }
            )
        }
    }
}
package com.nthlabs.esmes.feature_authentication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nthlabs.esmes.feature_authentication.domain.model.Home
import com.nthlabs.esmes.feature_authentication.domain.model.Login
import com.nthlabs.esmes.feature_authentication.presentation.login.component.LoginScreen
import com.nthlabs.esmes.feature_authentication.presentation.register.component.RegisterScreen

enum class LoginPaths{
    Login,
    Register
}

enum class HomePaths{
    Home
}

@Composable
fun Navigator(
    navController: NavHostController = rememberNavController(),
    login: Login
) {
    NavHost(navController = navController, startDestination = LoginPaths.Login.name) {
        // Login
        composable(route = LoginPaths.Login.name) {
            LoginScreen(onNavToHomeScreen = {
                navController.navigate(HomePaths.Home.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginPaths.Login.name) {
                        inclusive = true
                    }
                }
            },
                login = login
            ) {
                navController.navigate(LoginPaths.Register.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginPaths.Login.name) {
                        inclusive = true
                    }
                }
            }
        }
        // Register
        composable(route = LoginPaths.Register.name) {
            RegisterScreen(onNavToHomeScreen = {
                navController.navigate(HomePaths.Home.name) {
                    popUpTo(route = LoginPaths.Register.name) {
                        inclusive = true
                    }
                }
            },
                login = login
            ) {
                navController.navigate(LoginPaths.Login.name)
            }
        }
        // HomeScreen
        composable(route = HomePaths.Home.name) {
            // Permission()
            Home()
        }

    }
}
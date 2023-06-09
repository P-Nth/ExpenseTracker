package com.nthlabs.esmes.feature_authentication

import android.os.Bundle
import kotlinx.coroutines.delay
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.nthlabs.esmes.ui.theme.EsmesTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nthlabs.esmes.ui.theme.splashscreen.SplashScreen
import com.nthlabs.esmes.feature_authentication.domain.model.Login

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EsmesTheme {
                Esmes()
//                Text(text = "Hello World")
            }
        }
    }
}

@Composable
fun Esmes() {
    val navController = rememberNavController()
    val login = viewModel(modelClass = Login::class.java)
    NavHost(
        navController = navController,
        startDestination = "Splash_Screen"
    ) {
        composable("Splash_Screen") {
            SpScreen(navController = navController)
        }

        composable("Navigator") {
            Navigator(login = login)

        }
    }
}

@Composable
fun SpScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(4000)
        navController.navigate("Navigator")
    }
    SplashScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EsmesTheme {
        Esmes()
    }
}
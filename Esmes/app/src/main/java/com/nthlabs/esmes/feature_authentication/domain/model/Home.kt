package com.nthlabs.esmes.feature_authentication.domain.model

import androidx.compose.foundation.background
import com.nthlabs.esmes.ui.theme.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nthlabs.esmes.feature_cloud.presentation.main.UploadScreen
import com.nthlabs.esmes.feature_profile.presentation.main.ProfileScreen
import com.nthlabs.esmes.feature_analytics.presentation.main.AnalyticsScreen
import com.nthlabs.esmes.feature_authentication.presentation.home.HomeScreen
import com.nthlabs.esmes.feature_authentication.presentation.navigation.Items
import com.nthlabs.esmes.feature_authentication.presentation.navigation.TopNavBar
import com.nthlabs.esmes.feature_authentication.presentation.navigation.BottomNavBar

data class SectionData(
    val subtitle: String,
    val seeAll: Boolean,
    val bgColor: Color,
    val title: String,
    val time: String,
    val type: String,
    val size: Dp,
)

@Composable
fun Home(navController: NavHostController = rememberNavController()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavBar(navBarName = "Home", modifier = Modifier.fillMaxWidth(), bgColor = Color.Transparent)
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.91f)) {
                HomePage(navController = navController)
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(1f)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter) {
                BottomNavBar(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth(.9f),
                    bgColor = navig_blue
                )
            }
        }
    }
}

@Composable
fun HomePage(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Items.Home.id) {
        composable(route = Items.Home.id) {
            HomeScreen()
        }
        composable(route = Items.Analysis.id) {
            AnalyticsScreen()
        }
        composable(route = Items.Upload.id) {
            UploadScreen()
        }
        composable(route = Items.Profile.id) {
            ProfileScreen()
        }
    }
}

@Preview
@Composable
fun Prev3() {
    Home()
}
package com.nthlabs.esmes.feature_authentication.presentation.navigation

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.navigation.NavHostController
import com.nthlabs.esmes.R
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.nthlabs.esmes.ui.theme.*

sealed class Items(
    val id:String,
    var title:String,
    val icon: Int
) {

    object Home: Items("home", "Home", R.drawable.home_64)
    object Analysis: Items("analysis", "Analysis", R.drawable.chart_64)
    object Upload: Items("upload", "Upload", R.drawable.upload_64)
    object Profile: Items("profile", "Profile", R.drawable.user_64)

    object ItemsIn{
        val screens = listOf(
            Home, Analysis, Upload, Profile
        )
    }
}

@Composable
fun BottomNavBar(navController: NavHostController, modifier: Modifier, bgColor: Color) {
    Box() {
        BNavComponent(
            modifier = modifier.align(Alignment.TopCenter).padding(bottom = 5.dp),
            navController = navController,
            bgColor = bgColor,
        )
    }
}

@Composable
fun BNavComponent(
    navController: NavHostController,
    modifier: Modifier,
    bgColor: Color
) {
    val items = Items.ItemsIn.screens

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedScreen = items.any { it.id == currentDestination?.route }

    Row(
        modifier = modifier
            .clip(Shapes.medium)
            .background(bgColor)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (selectedScreen) {
            items.forEach { screen ->
                BNavComponents (
                    screen = screen,
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        }
    }
}

@Composable
fun BNavComponents (
    screen: Items,
    navController: NavHostController,
    currentDestination: NavDestination?
) {

    val isSelected = currentDestination?.hierarchy?.any() { it.route == screen.id } == true
    val bgColor = if (isSelected) navig_blue.copy(.1f) else Color.Transparent
    val contentColor = if (isSelected) primary_light_blue else black

    Box(
        modifier = Modifier
            .clip(Shapes.medium)
            .background(bgColor)
            .clickable(onClick = {
                navController.navigate(screen.id) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, start = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = screen.icon),
                contentDescription = null, tint = contentColor
            )
            AnimatedVisibility(visible = isSelected) {
                Text(text = screen.title, color = contentColor)
            }
        }
    }
}

@Preview
@Composable
fun Prev5(navController: NavHostController = rememberNavController()) {
    BottomNavBar(
        navController = navController,
        modifier = Modifier.fillMaxWidth(),
        bgColor = navig_blue
    )
}
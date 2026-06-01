package com.dronelogger.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dronelogger.app.ui.screen.AddDroneScreen
import com.dronelogger.app.ui.screen.AddFlightScreen
import com.dronelogger.app.ui.screen.DroneScreen
import com.dronelogger.app.ui.screen.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddFlight : Screen("add_flight")
    object Drones : Screen("drones")
    object AddDrone : Screen("add_drone")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAddFlightClick = {
                    navController.navigate(Screen.AddFlight.route)
                }
            )
        }

        composable(Screen.AddFlight.route) {
            AddFlightScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Drones.route) {
            DroneScreen(
                onAddDroneClick = {
                    navController.navigate(Screen.AddDrone.route)
                }
            )
        }

        composable(Screen.AddDrone.route) {
            AddDroneScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

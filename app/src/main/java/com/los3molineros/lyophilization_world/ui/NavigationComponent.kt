package com.los3molineros.lyophilization_world.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.los3molineros.lyophilization_world.ui.screens.LoginWithEmailActivity
import com.los3molineros.lyophilization_world.ui.screens.SplashActivity

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splashScreen") {

        composable( route = "splashScreen") {
            SplashActivity() {
                navController.navigate("firebase_email")
            }
        }

        composable( route = "firebase_email") {
            LoginWithEmailActivity()
        }

    }
}
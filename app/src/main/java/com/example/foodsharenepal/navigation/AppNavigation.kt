package com.example.foodsharenepal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodsharenepal.screens.AddFoodScreen
import com.example.foodsharenepal.screens.EditFoodScreen
import com.example.foodsharenepal.screens.HomeScreen
import com.example.foodsharenepal.screens.LoginScreen
import com.example.foodsharenepal.screens.ProfileScreen
import com.example.foodsharenepal.screens.RegisterScreen
import com.example.foodsharenepal.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // Login
        composable("login") {
            LoginScreen(navController)
        }

        // Register
        composable("register") {
            RegisterScreen(navController)
        }

        // Home
        composable("home") {
            HomeScreen(navController)
        }

        // Add Food
        composable("addFood") {
            AddFoodScreen(navController)
        }

        // Edit Food
        composable("editFood/{foodId}") { backStackEntry ->

            val foodId = backStackEntry.arguments?.getString("foodId") ?: ""

            EditFoodScreen(
                navController = navController,
                foodId = foodId
            )

        }

        // Profile
        composable("profile") {
            ProfileScreen(navController)
        }
        composable("splash") {
            SplashScreen(navController)
        }

    }

}
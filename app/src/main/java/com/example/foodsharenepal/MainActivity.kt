package com.example.foodsharenepal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.foodsharenepal.navigation.AppNavigation
import com.example.foodsharenepal.ui.theme.FoodShareNepalTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodShareNepalTheme {
                AppNavigation()
            }
        }
    }
}
package com.example.foodsharenepal.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.foodsharenepal.R
import com.example.foodsharenepal.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel = remember { AuthViewModel() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.foodshare_logo),
            contentDescription = "FoodShare Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(R.drawable.foodshare_logo),
            contentDescription = "FoodShare Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(R.drawable.login_food),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Welcome Back",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Login to continue sharing food"
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {

                viewModel.login(
                    email,
                    password
                ) { success, message ->

                    Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()

                    if (success) {

                        navController.navigate("home") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }

                    }

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(15.dp)
        ) {

            Text("Login")

        }

        Spacer(modifier = Modifier.height(15.dp))

        TextButton(
            onClick = {
                navController.navigate("register")
            }
        ) {

            Text("Don't have an account? Register")

        }

    }

}
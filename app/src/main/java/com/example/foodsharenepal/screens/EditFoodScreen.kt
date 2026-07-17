package com.example.foodsharenepal.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodsharenepal.R
import com.example.foodsharenepal.model.Food
import com.example.foodsharenepal.viewmodel.FoodViewModel

@Composable
fun EditFoodScreen(
    navController: NavController,
    foodId: String
) {

    val context = LocalContext.current
    val viewModel = remember { FoodViewModel() }

    var foodName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        viewModel.getFoodById(foodId) { food ->

            food?.let {

                foodName = it.foodName
                description = it.description
                quantity = it.quantity
                location = it.location
                expiryDate = it.expiryDate

            }

        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.food_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Update Food",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text("Edit your donated food information")

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Food Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Pickup Location") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = expiryDate,
            onValueChange = { expiryDate = it },
            label = { Text("Expiry Date") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {

                val food = Food(
                    id = foodId,
                    foodName = foodName,
                    description = description,
                    quantity = quantity,
                    location = location,
                    expiryDate = expiryDate
                )

                viewModel.updateFood(food) { success ->

                    if (success) {

                        Toast.makeText(
                            context,
                            "Food Updated Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.popBackStack()

                    } else {

                        Toast.makeText(
                            context,
                            "Update Failed",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            }
        ) {

            Text("Update Food")

        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                navController.popBackStack()

            }
        ) {

            Text("Cancel")

        }

    }

}
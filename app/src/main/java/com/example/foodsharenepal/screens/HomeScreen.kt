package com.example.foodsharenepal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodsharenepal.R
import com.example.foodsharenepal.model.Food
import com.example.foodsharenepal.utils.getFoodImage
import com.example.foodsharenepal.viewmodel.FoodViewModel
import androidx.compose.material.icons.filled.Search


@Composable
fun HomeScreen(navController: NavController) {

    val viewModel = remember { FoodViewModel() }
    var searchText by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedFood by remember { mutableStateOf<Food?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadFoods()
    }
    val filteredFoods = viewModel.foodList.filter {

        it.foodName.contains(searchText, ignoreCase = true) ||
                it.location.contains(searchText, ignoreCase = true)

    }
    if (showDeleteDialog && selectedFood != null) {

        AlertDialog(

            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text("Delete Food")
            },

            text = {
                Text("Are you sure you want to delete this donation?")
            },

            confirmButton = {

                Button(
                    onClick = {

                        viewModel.deleteFood(selectedFood!!.id) { success ->

                            if (success) {
                                viewModel.loadFoods()
                            }

                        }

                        showDeleteDialog = false

                    }
                ) {
                    Text("Delete")
                }

            },

            dismissButton = {

                OutlinedButton(
                    onClick = {

                        showDeleteDialog = false

                    }
                ) {

                    Text("Cancel")

                }

            }

        )

    }

    Scaffold(



        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("addFood")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Food")
            }

        }


    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.foodshare_logo),
                    contentDescription = "FoodShare Logo",
                    modifier = Modifier.size(70.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        text = "FoodShare Nepal",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Share Food • Spread Happiness"
                    )
                }
            }
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search food or location...")
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.home_banner),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome 👋",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Share food. Reduce waste. Help people.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Available Foods",
                    style = MaterialTheme.typography.headlineSmall
                )

                Button(
                    onClick = {
                        navController.navigate("profile")
                    }
                ) {

                    Icon(
                        Icons.Default.Person,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Profile")

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.foodList.isEmpty()) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(R.drawable.empty_food),
                        contentDescription = null,
                        modifier = Modifier.size(220.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "No food donations yet.",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigate("addFood")
                        }
                    ) {
                        Text("Donate Food")
                    }

                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(filteredFoods) { food ->

                        FoodCard(
                            food = food,
                            imageRes = getFoodImage(food.foodName),

                            onEdit = {
                                navController.navigate("editFood/${food.id}")
                            },

                            onDelete = {

                                selectedFood = food
                                showDeleteDialog = true

                            }
                        )

                    }

                }

            }

        }

    }
    @Composable
    fun FoodCard(
        food: Food,
        imageRes: Int,
        onEdit: () -> Unit,
        onDelete: () -> Unit
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column {

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = food.foodName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = food.foodName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = food.description,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "📍 Location: ${food.location}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "📦 Quantity: ${food.quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "📅 Expiry: ${food.expiryDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        FilledTonalButton(
                            onClick = onEdit,
                            modifier = Modifier.weight(1f)
                        ) {

                            Text("✏ Edit")

                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Button(
                            onClick = onDelete,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            ),
                            modifier = Modifier.weight(1f)
                        ) {

                            Text("🗑 Delete")

                        }

                    }

                }

            }

        }

    }

}
@Composable
fun FoodCard(
    food: Food,
    imageRes: Int,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = food.foodName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = food.foodName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = food.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "📍 Location: ${food.location}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "📦 Quantity: ${food.quantity}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "📅 Expiry: ${food.expiryDate}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    FilledTonalButton(
                        onClick = onEdit,
                        modifier = Modifier.weight(1f)
                    ) {

                        Text("✏ Edit")

                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.weight(1f)
                    ) {

                        Text("🗑 Delete")

                    }

                }

            }

        }

    }

}
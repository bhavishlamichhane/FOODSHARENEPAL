package com.example.foodsharenepal.model

data class Food(
    val id: String = "",
    val foodName: String = "",
    val description: String = "",
    val quantity: String = "",
    val location: String = "",
    val expiryDate: String = "",
    val donorId: String = "",
    val imageUrl: String = "",
    val category: String = "Other"
)
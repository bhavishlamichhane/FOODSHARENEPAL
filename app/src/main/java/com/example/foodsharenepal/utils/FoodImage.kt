package com.example.foodsharenepal.utils



import com.example.foodsharenepal.R

fun getFoodImage(foodName: String): Int {

    return when (foodName.lowercase()) {

        "burger" -> R.drawable.burger

        "pizza" -> R.drawable.pizza

        "momo" -> R.drawable.momo

        "dal bhat",
        "dalbhat" -> R.drawable.dalbhat

        "fruits",
        "fruit" -> R.drawable.fruits

        "vegetables",
        "vegetable" -> R.drawable.vegetables

        else -> R.drawable.food_placeholder

    }

}
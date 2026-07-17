package com.example.foodsharenepal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.foodsharenepal.model.Food
import com.example.foodsharenepal.repository.FoodRepository

class FoodViewModel : ViewModel() {

    private val repository = FoodRepository()

    var foodList = mutableStateListOf<Food>()
        private set

    fun addFood(
        food: Food,
        onResult: (Boolean, String) -> Unit
    ) {
        repository.addFood(food, onResult)
    }

    fun loadFoods() {
        repository.getFoods {
            foodList.clear()
            foodList.addAll(it)
        }
    }

    fun updateFood(
        food: Food,
        onResult: (Boolean) -> Unit
    ) {
        repository.updateFood(food, onResult)
    }

    fun deleteFood(
        id: String,
        onResult: (Boolean) -> Unit
    ) {
        repository.deleteFood(id) {
            if (it) {
                loadFoods()
            }
            onResult(it)
        }
    }
    fun getFoodById(
        id: String,
        onResult: (Food?) -> Unit
    ) {
        repository.getFoodById(id, onResult)
    }
}
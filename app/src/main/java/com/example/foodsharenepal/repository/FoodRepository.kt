package com.example.foodsharenepal.repository

import com.example.foodsharenepal.model.Food
import com.google.firebase.firestore.FirebaseFirestore

class FoodRepository {

    private val db = FirebaseFirestore.getInstance()
    private val foodCollection = db.collection("foods")

    fun addFood(
        food: Food,
        onResult: (Boolean, String) -> Unit
    ) {

        val document = foodCollection.document()

        val newFood = food.copy(id = document.id)

        document.set(newFood)
            .addOnSuccessListener {
                onResult(true, "Food Added Successfully")
            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Failed")
            }
    }

    fun getFoods(
        onResult: (List<Food>) -> Unit
    ) {

        foodCollection.get()
            .addOnSuccessListener { result ->

                val foods = mutableListOf<Food>()

                for (document in result) {
                    val food = document.toObject(Food::class.java)
                    foods.add(food)
                }

                onResult(foods)

            }

    }

    fun updateFood(
        food: Food,
        onResult: (Boolean) -> Unit
    ) {

        foodCollection.document(food.id)
            .set(food)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }

    }

    fun deleteFood(
        id: String,
        onResult: (Boolean) -> Unit
    ) {

        foodCollection.document(id)
            .delete()
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }

    }
    fun getFoodById(
        id: String,
        onResult: (Food?) -> Unit
    ) {

        foodCollection.document(id)
            .get()
            .addOnSuccessListener {

                onResult(it.toObject(Food::class.java))

            }
            .addOnFailureListener {

                onResult(null)

            }

    }


}
fun deleteFood(
    foodId: String,
    onResult: (Boolean) -> Unit
) {
    db.collection("foods")
        .document(foodId)
        .delete()
        .addOnSuccessListener {
            onResult(true)
        }
        .addOnFailureListener {
            onResult(false)
        }
}
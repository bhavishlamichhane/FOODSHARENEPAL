package com.example.foodsharenepal.repository

import com.example.foodsharenepal.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun registerUser(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                val uid = auth.currentUser!!.uid

                val user = User(
                    uid = uid,
                    fullName = fullName,
                    email = email,
                    phone = phone
                )

                db.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        onResult(true, "Registration Successful")
                    }
                    .addOnFailureListener {
                        onResult(false, it.message ?: "Error")
                    }

            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Error")
            }
    }

    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true, "Login Successful")
            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Login Failed")
            }
    }

    fun getUserProfile(
        onResult: (User?) -> Unit
    ) {

        val uid = auth.currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {

                onResult(it.toObject(User::class.java))

            }
            .addOnFailureListener {

                onResult(null)

            }

    }

    fun updateProfile(
        user: User,
        onResult: (Boolean) -> Unit
    ) {

        db.collection("users")
            .document(user.uid)
            .set(user)
            .addOnSuccessListener {

                onResult(true)

            }
            .addOnFailureListener {

                onResult(false)

            }

    }

    fun logout() {
        auth.signOut()
    }

    fun getCurrentUser() = auth.currentUser
}
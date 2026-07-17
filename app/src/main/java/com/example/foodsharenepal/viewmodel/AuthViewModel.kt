package com.example.foodsharenepal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodsharenepal.model.User
import com.example.foodsharenepal.repository.AuthRepository

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    fun register(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        repository.registerUser(
            fullName,
            email,
            phone,
            password,
            onResult
        )
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        repository.loginUser(
            email,
            password,
            onResult
        )
    }

    fun getProfile(
        onResult: (User?) -> Unit
    ) {
        repository.getUserProfile(onResult)
    }

    fun updateProfile(
        user: User,
        onResult: (Boolean) -> Unit
    ) {
        repository.updateProfile(user, onResult)
    }

    fun logout() {
        repository.logout()
    }

    fun isLoggedIn() = repository.getCurrentUser() != null
}
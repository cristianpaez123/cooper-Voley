package com.example.login.data.repository

import com.example.coopervoley.domain.model.LoginResult
import com.google.firebase.auth.FirebaseUser

interface AuthProvider {
    suspend fun login(email: String, password: String): LoginResult
}
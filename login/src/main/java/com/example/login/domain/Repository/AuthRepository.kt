package com.example.coopervoley.domain.Repository

import com.example.coopervoley.domain.model.LoginResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResult
}
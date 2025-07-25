package com.example.login.data.repository

import com.example.coopervoley.domain.model.LoginResult

interface AuthProvider {
    suspend fun login(email: String, password: String): LoginResult
}
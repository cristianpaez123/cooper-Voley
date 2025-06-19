package com.example.coopervoley.domain.Repository

import com.example.coopervoley.domain.model.LoginResult

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResult
}
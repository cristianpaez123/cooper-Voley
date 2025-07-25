package com.example.login.data.repository

import com.example.coopervoley.domain.Repository.AuthRepository
import com.example.coopervoley.domain.model.LoginResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authProvider: AuthProvider
) : AuthRepository {

    override suspend fun login(email: String, password: String): LoginResult {
        return authProvider.login(email, password)
    }

}
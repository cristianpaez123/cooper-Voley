package com.example.coopervoley

import com.example.login.data.repository.AuthProvider
import com.example.login.data.repository.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthProvider(firebaseAuth: FirebaseAuth): AuthProvider =
        FirebaseAuthProvider(firebaseAuth)
}

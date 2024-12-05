package com.example.kotlin_social.auth.domain.repository

import com.example.kotlin_social.auth.domain.model.AuthResultData
import dev.gitlive.firebase.auth.FirebaseAuth





class AuthRepository {





    private val firebaseAuth: FirebaseAuth = FirebaseAuth.

    // Sign Up method
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {

        return try {
            // Create a user with email and password
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            // Update the display name of the user
            authResult.user?.updateProfile(
                com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            )?.await()

            Result.success(AuthResultData(authResult.user, "Sign up successful"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Sign In method
    suspend fun signIn(
        email: String,
        password: String
    ): Result<AuthResultData> {
        return try {
            // Sign in the user with email and password
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(AuthResultData(authResult.user, "Sign in successful"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
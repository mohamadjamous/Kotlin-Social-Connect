package com.example.kotlin_social.auth.data

import com.example.kotlin_social.auth.domain.model.AuthResultData
import com.example.kotlin_social.auth.domain.repository.AuthRepository
import com.example.kotlin_social.common.data.local.UserPreferences
import com.example.kotlin_social.common.data.local.toUserSettings
import com.example.kotlin_social.common.util.DispatcherProvider
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val userPreferences: UserPreferences,
    private val firebaseAuth: FirebaseAuth = Firebase.auth // FirebaseAuth initialization
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                // Create user with Firebase Auth
                val user = firebaseAuth.createUserWithEmailAndPassword(email, password)

                // Update profile with display name
                user.user?.updateProfile (
                    displayName = name
                )

                // Map user data to AuthResultData
                val authResultData = AuthResultData(
                    id = user.user?.uid ?: "",
                    name = user.user?.displayName ?: name,
                    bio = "New user",
                    avatar = null,
                    followersCount = 0,
                    followingCount = 0
                )

                // Store user data in preferences
                userPreferences.setUserData(authResultData.toUserSettings())

                Result.success(authResultData)
            } catch (e: Exception) {
                Result.failure(Exception("Oops, we could not process your request. Please try again later."))
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                // Sign in user with Firebase Auth
                val user = firebaseAuth.signInWithEmailAndPassword(email, password)

                // Map user data to AuthResultData
                val authResultData = AuthResultData(
                    id = user.user?.uid ?: "",
                    name = user.user?.displayName ?: "Unknown",
                    bio = "Existing user",
                    avatar = null,
                    followersCount = 0,
                    followingCount = 0
                )

                // Store user data in preferences
                userPreferences.setUserData(authResultData.toUserSettings())

                Result.success(authResultData)
            } catch (e: Exception) {
                Result.failure(Exception("Oops, we could not process your request. Please try again later."))
            }
        }
    }
}







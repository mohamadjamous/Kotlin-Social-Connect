package com.example.kotlin_social.auth.domain.repository

import com.example.kotlin_social.auth.domain.model.AuthResultData

internal interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData>

    suspend fun signIn(email: String, password: String): Result<AuthResultData>
}

//class AuthRepository {
//
//    private val firebaseAuth: FirebaseAuth = Firebase.auth
//
//    // Sign Up method
//    suspend fun signUp(
//        name: String,
//        email: String,
//        password: String
//    ): Result<AuthResultData> {
//        return try {
//            // Create a user with email and password
//            val user = firebaseAuth.createUserWithEmailAndPassword(email, password)
//
//            // Update the display name of the user
//            user.user?.updateProfile(displayName = name)
//
//            // Create AuthResultData object
//            val authResultData = AuthResultData(
//                id = user.user?.uid ?: "",
//                name = user.user?.displayName ?: name,
//                bio = "New user",
//                avatar = null,
//                followersCount = 0,
//                followingCount = 0
//            )
//
//            Result.success(authResultData)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    // Sign In method
//    suspend fun signIn(
//        email: String,
//        password: String
//    ): Result<AuthResultData> {
//        return try {
//            // Sign in the user with email and password
//            val user = firebaseAuth.signInWithEmailAndPassword(email, password)
//
//            // Create AuthResultData object
//            val authResultData = AuthResultData(
//                id = user.user?.uid ?: "",
//                name = user.user?.displayName ?: "Unknown",
//                bio = "Existing user",
//                avatar = null,
//                followersCount = 0,
//                followingCount = 0
//            )
//
//            Result.success(authResultData)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}


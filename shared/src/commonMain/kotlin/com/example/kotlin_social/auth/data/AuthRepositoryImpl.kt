package com.example.kotlin_social.auth.data

import com.example.kotlin_social.auth.domain.model.AuthResultData
import com.example.kotlin_social.auth.domain.repository.AuthRepository
import com.example.kotlin_social.common.data.local.UserPreferences
import com.example.kotlin_social.common.data.local.toUserSettings
import com.example.kotlin_social.common.util.DispatcherProvider
import com.example.kotlin_social.common.util.Result
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
//    private val firebaseAuth: FirebaseAuth = Firebase.auth, // FirebaseAuth initialization
    private val authService: AuthService,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {

        // right shared firebase code for sign in and sign up

        return withContext(dispatcher.io) {
            try {

//                val request = SignUpRequest(name, email, password)
//                val authResponse = authService.signUp(request)
//
//                if (authResponse.data == null){
//                    kotlin.Result.Error(
//                        message = authResponse.errorMessage!!
//                    )
//                }else{
//                    userPreferences.setUserData(
//                        authResponse.data.toAuthResultData().toUserSettings()
//                    )
//                    kotlin.Result.Success(
//                        data = authResponse.data.toAuthResultData()
//                    )
//                }

//                // Create user with Firebase Auth
//                val user = firebaseAuth.createUserWithEmailAndPassword(email, password)

                // Update profile with display name
//                user.user?.updateProfile (
//                    displayName = name
//                )

                // Map user data to AuthResultData
                val authResultData = AuthResultData(
                    id = "",
                    name = "",
                    bio = "New user",
                    avatar = null,
                    followersCount = 0,
                    followingCount = 0
                )

                // Store user data in preferences
                userPreferences.setUserData(authResultData.toUserSettings())

                Result.Success(authResultData)
            } catch (e: Exception) {
                Result.Error(message = "Oops, we could not process your request. Please try again later.")
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {

//                val request = SignInRequest(email, password)
//
//                val authResponse = authService.signIn(request)
//
//                if (authResponse.data == null){
//                    kotlin.Result.Error(
//                        message = authResponse.errorMessage!!
//                    )
//                }else{
//                    userPreferences.setUserData(
//                        authResponse.data.toAuthResultData().toUserSettings()
//                    )
//                    kotlin.Result.Success(
//                        data = authResponse.data.toAuthResultData()
//                    )
//                }

                // Sign in user with Firebase Auth
//                val user = firebaseAuth.signInWithEmailAndPassword(email, password)

                // Map user data to AuthResultData
                val authResultData = AuthResultData(
                    id = "",
                    name = "",
                    bio = "New user",
                    avatar = null,
                    followersCount = 0,
                    followingCount = 0
                )

                // Store user data in preferences
                userPreferences.setUserData(authResultData.toUserSettings())

                Result.Success(authResultData)
            } catch (e: Exception) {
                Result.Error(message = "Oops, we could not process your request. Please try again later.")
            }
        }
    }
}







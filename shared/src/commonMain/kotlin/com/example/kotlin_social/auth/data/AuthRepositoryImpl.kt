package com.example.kotlin_social.auth.data

import com.example.kotlin_social.auth.domain.model.AuthResultData
import com.example.kotlin_social.auth.domain.repository.AuthRepository
import com.example.kotlin_social.common.data.local.UserPreferences
import com.example.kotlin_social.common.util.DispatcherProvider
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val userPreferences: UserPreferences
) : AuthRepository {


    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {


        return withContext(dispatcher.io){
            try {
                val request = SignUpRequest(name, email, password)

                val authResponse = authService.signUp(request)

                if (authResponse.data == null){
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                }else{
                    userPreferences.setUserData(
                        authResponse.data.toAuthResultData().toUserSettings()
                    )
                    Result.Success(
                        data = authResponse.data.toAuthResultData()
                    )
                }
            }catch (e: Exception){
                Result.Error(
                    message = "Oops, we could not send your request, try later!"
                )
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io){
            try {
                val request = SignInRequest(email, password)

                val authResponse = authService.signIn(request)

                if (authResponse.data == null){
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                }else{
                    userPreferences.setUserData(
                        authResponse.data.toAuthResultData().toUserSettings()
                    )
                    Result.Success(
                        data = authResponse.data.toAuthResultData()
                    )
                }
            }catch (e: Exception){
                Result.Error(
                    message = "Oops, we could not send your request, try later!"
                )
            }
        }
    }
}










package com.example.kotlin_social.auth.domain.repository

import com.example.kotlin_social.auth.domain.model.AuthResultData
import com.example.kotlin_social.common.util.Result

interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData>

    suspend fun signIn(
        email: String,
        password: String
    ): Result<AuthResultData>
}



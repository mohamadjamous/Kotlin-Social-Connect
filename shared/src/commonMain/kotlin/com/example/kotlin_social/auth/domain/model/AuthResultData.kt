package com.example.kotlin_social.auth.domain.model

data class AuthResultData(
    val uid: String, val userName: String, val email: String,
    val bio: String, val password: String, val avatar: String? = null,
    val followersCount: Int = 0, val followingCount: Int = 0
)
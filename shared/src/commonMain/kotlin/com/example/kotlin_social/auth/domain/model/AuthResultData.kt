package com.example.kotlin_social.auth.domain.model

data class AuthResultData(
    val id: String,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)
package com.example.kotlin_social.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class User(val uid: String, val userName: String, val email: String,
    val bio: String, val password: String, val avatar: String,
    val followersCount: Int, val followingCount: Int){

    constructor() : this("", "", "", "", "", "")
}



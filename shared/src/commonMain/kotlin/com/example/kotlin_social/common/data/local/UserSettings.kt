package com.example.kotlin_social.common.data.local

import com.example.kotlin_social.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable








@Serializable
data class UserSettings(
    val id: String = "",
    val name: String = "",
    val bio: String = "",
    val avatar: String? = null,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)

fun UserSettings.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, followersCount, followingCount)
}

fun AuthResultData.toUserSettings(): UserSettings {
    return UserSettings(id, name, bio, avatar, followersCount, followingCount)
}
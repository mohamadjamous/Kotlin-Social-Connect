package com.example.kotlin_social.android.home.onboarding

import com.example.kotlin_social.android.common.fake_data.FollowsUser


data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = listOf(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)

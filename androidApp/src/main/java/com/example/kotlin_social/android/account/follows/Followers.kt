package com.example.kotlin_social.android.account.follows

import androidx.compose.runtime.Composable
import com.example.kotlin_social.android.destinations.ProfileDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun Followers(
    navigator: DestinationsNavigator,
    userId: Int
) {
    val viewModel: FollowsViewModel = koinViewModel()

    FollowsScreen(
        uiState = viewModel.uiState,
        fetchFollows = { viewModel.fetchFollows(userId, 2) },
        onItemClick = {navigator.navigate(ProfileDestination(it))}
    )
}
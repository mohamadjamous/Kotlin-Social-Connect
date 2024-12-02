package com.example.kotlin_social.android.home

import androidx.compose.runtime.Composable
import com.example.kotlin_social.android.destinations.PostDetailDestination
import com.example.kotlin_social.android.destinations.ProfileDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun Home(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()
    

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsFeedUiState = viewModel.postsUiState,
        onPostClick = {
                      navigator.navigate(PostDetailDestination(it.id))
        },
        onProfileClick = { navigator.navigate(ProfileDestination(it)) },
        onLikeClick = {},
        onCommentClick = {},
        onUserClick = {},
        onFollowButtonClick = {_, _ ->} ,
        onBoardingFinish = {  },
        fetchMoreData = { viewModel.fetchData() })
}
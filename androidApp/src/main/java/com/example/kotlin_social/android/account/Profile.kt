package com.example.kotlin_social.android.account

import androidx.compose.runtime.Composable
import com.example.kotlin_social.android.destinations.EditProfileDestination
import com.example.kotlin_social.android.destinations.FollowersDestination
import com.example.kotlin_social.android.destinations.FollowingDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun Profile(userId: Int, navigator: DestinationsNavigator) {

    val viewModel: ProfileViewModel = koinViewModel()






    ProfileScreen(
        userInfoUiState = viewModel.userInfoUiState,
        profilePostsUiState = viewModel.profilePostsUiState,
        onButtonClick = { navigator.navigate(EditProfileDestination(userId)) },
        onFollowersClick = { navigator.navigate(FollowersDestination(userId)) },
        onFollowingClick = { navigator.navigate(FollowingDestination(userId)) },
        onPostClick = { },
        onLikeClick = { },
        onCommentClick = { },
        fetchData = { viewModel.fetchProfile(userId) }
    )

}

package com.example.kotlin_social.android.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_social.android.common.fake_data.Post
import com.example.kotlin_social.android.common.fake_data.Profile
import com.example.kotlin_social.android.common.fake_data.samplePosts
import com.example.kotlin_social.android.common.fake_data.sampleProfiles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class UserInfoUiState(
    val isLoading: Boolean = true,
    val profile: Profile? = null,
    val errorMessage: String? = null
)

data class ProfilePostsUiState(
    val isLoading: Boolean = true,
    val posts: List<Post> = listOf(),
    val errorMessage: String? = null,
    val endReached: Boolean = false
)

sealed interface ProfileUiAction {
    data class FetchProfileAction(val profileId: Long) : ProfileUiAction
    data class FollowUserAction(val profile: Profile) : ProfileUiAction

    data object LoadMorePostsAction : ProfileUiAction

    data class PostLikeAction(val post: Post) : ProfileUiAction
}


class ProfileViewModel : ViewModel(){


    var userInfoUiState by mutableStateOf(UserInfoUiState())
        private set

    var profilePostsUiState by mutableStateOf(ProfilePostsUiState())
        private set



    fun fetchProfile(userId: Int){
        viewModelScope.launch {
            delay(1000)

            userInfoUiState = userInfoUiState.copy(
                isLoading = false,
                profile = sampleProfiles.find { it.id == userId }
            )


            profilePostsUiState = profilePostsUiState.copy(
                isLoading = false,
                posts = samplePosts
            )


        }
    }

}
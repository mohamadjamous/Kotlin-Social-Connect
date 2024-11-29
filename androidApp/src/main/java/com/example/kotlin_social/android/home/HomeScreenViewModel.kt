package com.example.kotlin_social.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_social.android.common.fake_data.FollowsUser
import com.example.kotlin_social.android.common.fake_data.Post
import com.example.kotlin_social.android.common.fake_data.samplePosts
import com.example.kotlin_social.android.common.fake_data.sampleUsers
import com.example.kotlin_social.android.home.onboarding.OnBoardingUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

   var postsUiState by mutableStateOf(PostsFeedUiState())
       private set


    var onBoardingUiState by mutableStateOf(OnBoardingUiState())
        private set



    init {
        fetchData()

    }

    fun fetchData(){
       onBoardingUiState = onBoardingUiState.copy(isLoading = true)
        postsUiState = postsUiState.copy(isLoading = true)

        viewModelScope.launch {
            delay(1000)

            onBoardingUiState = onBoardingUiState.copy(
                isLoading = false,
                users = sampleUsers,
                shouldShowOnBoarding = true
            )

            postsUiState = postsUiState.copy(
                isLoading = false,
                posts = samplePosts
            )
        }
    }

//    private fun createPagingManager(): PagingManager<Post>{
//        return DefaultPagingManager(
//            onRequest = {page ->
//                getPostsUseCase(page, Constants.DEFAULT_REQUEST_PAGE_SIZE)
//            },
//            onSuccess = {posts, page ->
//                postsFeedUiState = if (posts.isEmpty()){
//                    postsFeedUiState.copy(endReached = true)
//                }else{
//                    if (page == Constants.INITIAL_PAGE_NUMBER){
//                        postsFeedUiState = postsFeedUiState.copy(posts = emptyList())
//                    }
//                    postsFeedUiState.copy(
//                        posts = postsFeedUiState.posts + posts,
//                        endReached = posts.size < Constants.DEFAULT_REQUEST_PAGE_SIZE
//                    )
//                }
//            },
//            onError = {cause, page ->
//                if (page == Constants.INITIAL_PAGE_NUMBER){
//                    homeRefreshState = homeRefreshState.copy(
//                        refreshErrorMessage = cause
//                    )
//                }else{
//                    postsFeedUiState = postsFeedUiState.copy(
//                        loadingErrorMessage = cause
//                    )
//                }
//            },
//            onLoadStateChange = {isLoading ->
//                postsFeedUiState = postsFeedUiState.copy(
//                    isLoading = isLoading
//                )
//            }
//        )
//    }

//    private fun loadMorePosts() {
//        if (postsFeedUiState.endReached) return
//        viewModelScope.launch {
//            pagingManager.loadItems()
//        }
//    }

//    private fun handleOnBoardingResult(result: Result<List<FollowsUser>>) {
//        when (result) {
//            is Result.Error -> Unit
//
//            is Result.Success -> {
//                result.data?.let { followsUsers ->
//                    onBoardingUiState = onBoardingUiState.copy(
//                        shouldShowOnBoarding = followsUsers.isNotEmpty(),
//                        followableUsers = followsUsers
//                    )
//                }
//            }
//        }
//    }

//    private fun followUser(followsUser: FollowsUser) {
//        viewModelScope.launch {
//            val result = followOrUnfollowUseCase(
//                followedUserId = followsUser.id,
//                shouldFollow = !followsUser.isFollowing
//            )
//
//            onBoardingUiState = onBoardingUiState.copy(
//                followableUsers = onBoardingUiState.followableUsers.map {
//                    if (it.id == followsUser.id) {
//                        it.copy(isFollowing = !followsUser.isFollowing)
//                    } else it
//                }
//            )
//
//            when (result) {
//                is Result.Error -> {
//                    onBoardingUiState = onBoardingUiState.copy(
//                        followableUsers = onBoardingUiState.followableUsers.map {
//                            if (it.id == followsUser.id) {
//                                it.copy(isFollowing = followsUser.isFollowing)
//                            } else it
//                        }
//                    )
//                }
//
//                is Result.Success -> Unit
//            }
//        }
//    }

//    private fun dismissOnboarding() {
//        val hasFollowing = onBoardingUiState.followableUsers.any { it.isFollowing }
//        if (!hasFollowing) {
//            //TODO tell the user they need to follow at least one person
//        } else {
//            onBoardingUiState =
//                onBoardingUiState.copy(shouldShowOnBoarding = false, followableUsers = emptyList())
//            fetchData()
//        }
//    }

//    private fun likeOrUnlikePost(post: Post) {
//        viewModelScope.launch {
//            val count = if (post.isLiked) -1 else +1
//            postsFeedUiState = postsFeedUiState.copy(
//                posts = postsFeedUiState.posts.map {
//                    if (it.postId == post.postId) {
//                        it.copy(
//                            isLiked = !post.isLiked,
//                            likesCount = post.likesCount.plus(count)
//                        )
//                    } else it
//                }
//            )
//
//            val result = likePostUseCase(
//                post = post,
//            )
//
//            when (result) {
//                is Result.Error -> {
//                    updatePost(post)
//                }
//
//                is Result.Success -> Unit
//            }
//        }
//    }

//    private fun updatePost(post: Post) {
//        postsFeedUiState = postsFeedUiState.copy(
//            posts = postsFeedUiState.posts.map {
//                if (it.postId == post.postId) post else it
//            }
//        )
//    }
//
//    private fun insertNewPost(post: Post){
//        postsFeedUiState = postsFeedUiState.copy(
//            posts = listOf(post) + postsFeedUiState.posts
//        )
//    }
//
//    private fun updateCurrentUserPostsProfileData(profile: Profile) {
//        postsFeedUiState = postsFeedUiState.copy(
//            posts = postsFeedUiState.posts.map {
//                if (it.userId == profile.id) {//should use it.isOwnComment
//                    it.copy(
//                        userName = profile.name,
//                        userImageUrl = profile.imageUrl
//                    )
//                } else {
//                    it
//                }
//            }
//        )
//    }

//    fun onUiAction(uiAction: HomeUiAction) {
//        when (uiAction) {
//            is HomeUiAction.FollowUserAction -> followUser(uiAction.user)
//            HomeUiAction.LoadMorePostsAction -> loadMorePosts()
//            is HomeUiAction.PostLikeAction -> likeOrUnlikePost(uiAction.post)
//            HomeUiAction.RefreshAction -> fetchData()
//            HomeUiAction.RemoveOnboardingAction -> dismissOnboarding()
//        }
//    }

}

data class HomeRefreshState(
    val isRefreshing: Boolean = false,
    val refreshErrorMessage: String? = null
)

data class OnBoardingUiState(
    val shouldShowOnBoarding: Boolean = false,
    val followableUsers: List<FollowsUser> = listOf()
)

data class PostsFeedUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = listOf(),
    val loadingErrorMessage: String? = null,
    val endReached: Boolean = false
)

sealed interface HomeUiAction {
    data class FollowUserAction(val user: FollowsUser) : HomeUiAction
    data class PostLikeAction(val post: Post) : HomeUiAction
    data object RemoveOnboardingAction : HomeUiAction
    data object RefreshAction : HomeUiAction
    data object LoadMorePostsAction : HomeUiAction
}
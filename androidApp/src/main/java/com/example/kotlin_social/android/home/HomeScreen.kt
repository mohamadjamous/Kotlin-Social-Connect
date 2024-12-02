package com.example.kotlin_social.android.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_social.android.R
import com.example.kotlin_social.android.common.components.PostListItem
import com.example.kotlin_social.android.common.fake_data.FollowsUser
import com.example.kotlin_social.android.common.fake_data.Post
import com.example.kotlin_social.android.common.fake_data.samplePosts
import com.example.kotlin_social.android.common.fake_data.sampleUsers
import com.example.kotlin_social.android.common.theming.LargeSpacing
import com.example.kotlin_social.android.common.theming.SocialAppTheme
import com.example.kotlin_social.android.home.onboarding.OnBoardingSection
import com.example.kotlin_social.android.home.onboarding.OnBoardingUiState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postsFeedUiState: PostsFeedUiState,
    onPostClick: ((Post) -> Unit)? = null,
    onProfileClick: (userId: Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onUserClick: (FollowsUser) -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    fetchMoreData: () -> Unit
) {


    val pullRefreshState = rememberPullRefreshState(
        refreshing = onBoardingUiState.isLoading && postsFeedUiState.isLoading,
        onRefresh = { fetchMoreData()  }
    )


    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            if (onBoardingUiState.shouldShowOnBoarding) {
                item {
                    OnBoardingSection(
                        users = onBoardingUiState.users,
                        onUserClick = { onProfileClick(it.id) },
                        onFollowButtonClick = onFollowButtonClick
                    ) {
                        onBoardingFinish()
                    }

//                    Text(
//                        text = stringResource(id = R.string.trending_posts_title),
//                        style = MaterialTheme.typography.subtitle1,
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(bottom = LargeSpacing),
//                        textAlign = TextAlign.Center
//                    )

                }
            }

            items(items = postsFeedUiState.posts, key = { post -> post.id}){

                PostListItem(
                    post = it, onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommentClick = onCommentClick,
                    onPostClick = onPostClick
                )

            }
        }

        PullRefreshIndicator(
            refreshing = onBoardingUiState.isLoading && postsFeedUiState.isLoading,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )

    }
}



@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    SocialAppTheme {
        Surface {
            HomeScreen(
                onBoardingUiState = OnBoardingUiState(
                    users = sampleUsers,
                    shouldShowOnBoarding = true
                ),
                postsFeedUiState = PostsFeedUiState(posts = samplePosts),
                onProfileClick = { },
                onLikeClick = {},
                onCommentClick = {},
                onUserClick = {},
                onFollowButtonClick = {_, _ ->},
                onBoardingFinish = { }) {
                
            }
        }
    }
}
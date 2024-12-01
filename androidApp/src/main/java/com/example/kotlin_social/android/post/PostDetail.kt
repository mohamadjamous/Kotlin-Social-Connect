package com.example.kotlin_social.android.post

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigator
import com.example.kotlin_social.android.common.fake_data.Post
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun PostDetail (
    navigator: DestinationsNavigator,
    postId: String){

    val viewModel: PostDetailScreenViewModel = koinViewModel()

    PostDetailScreen(
        postUiState = viewModel.postUiState,
        commentsUiState = viewModel.commentsUiState,
        onCommentMoreIconClick = {},
        onProfileClick = {},
        onAddCommentClick = {  },
        fetchData = {viewModel.fetchData(postId = postId)})
}
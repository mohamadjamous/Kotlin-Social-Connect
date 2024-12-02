package com.example.kotlin_social.android.post

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_social.android.R
import com.example.kotlin_social.android.common.components.CommentListItem
import com.example.kotlin_social.android.common.components.PostListItem
import com.example.kotlin_social.android.common.fake_data.Comment
import com.example.kotlin_social.android.common.fake_data.sampleComments
import com.example.kotlin_social.android.common.fake_data.samplePosts
import com.example.kotlin_social.android.common.theming.ExtraLargeSpacing
import com.example.kotlin_social.android.common.theming.LargeSpacing
import com.example.kotlin_social.android.common.theming.SocialAppTheme
import com.example.kotlin_social.android.common.util.Constants
import kotlinx.coroutines.launch


@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostUiState,
    commentsUiState: CommentsUiState,
    onCommentMoreIconClick: (Comment) -> Unit,
    onProfileClick: (Int) -> Unit,
    onAddCommentClick: () -> Unit,
    fetchData: () -> Unit
){

    if (postUiState.isLoading && commentsUiState.isLoading){
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else if (postUiState.post != null){
        LazyColumn(
            modifier = modifier.fillMaxWidth()
                .background(color = MaterialTheme.colors.surface)) {

            item (key = "post_item") {
                PostListItem(
                    post = postUiState.post,
                    onProfileClick = onProfileClick,
                    onLikeClick = {},
                    onCommentClick = {}
                )
            }

            item(key = Constants.COMMENTS_HEADER_KEY) {
                CommentsSectionHeader{
                    onAddCommentClick()
                }
            }


            items(items = sampleComments, key = { comment -> comment.id }){
                Divider()
                CommentListItem(comment = it, onProfileClick = onProfileClick) {
                    onCommentMoreIconClick(it)
                }
            }

        }


    }else{

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.loading_error_message),
                    style = MaterialTheme.typography.caption
                )
                OutlinedButton(
                    onClick = fetchData,
                    shape = RoundedCornerShape(percent = 50),
                    contentPadding = PaddingValues(horizontal = ExtraLargeSpacing)
                ) {
                    Text(text = stringResource(id = R.string.retry_button_text))
                }
            }
        }
    }


    LaunchedEffect(key1 = Unit, block = {
        fetchData()
    })

}


@Composable
fun CommentsSectionHeader(
    modifier: Modifier = Modifier,
    onAddCommentClick: () -> Unit
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = LargeSpacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.comments_label),
            style = MaterialTheme.typography.subtitle1,
        )


        OutlinedButton(onClick =  onAddCommentClick ) {

            Text(text = stringResource(id = R.string.new_comment_button_label))
            
        }
    }

}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostDetailPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostDetailScreen(
                postUiState = PostUiState(isLoading = false, post = samplePosts.first()),
                commentsUiState = CommentsUiState(isLoading = false, comments = sampleComments),
                onCommentMoreIconClick ={} ,
                onProfileClick = {},
                onAddCommentClick = { /*TODO*/ },
                fetchData = { })
        }
    }
}
package com.example.kotlin_social.android.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.kotlin_social.android.R
import com.example.kotlin_social.android.common.theming.LargeSpacing


@Composable
fun PostDetailScreen(
    modifier: Modifier
){

}


@Composable
fun CommentsSectionHeader(
    modifier: Modifier,
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
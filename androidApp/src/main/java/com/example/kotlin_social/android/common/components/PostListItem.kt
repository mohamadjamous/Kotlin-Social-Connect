package com.example.kotlin_social.android.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kotlin_social.android.R
import com.example.kotlin_social.android.common.fake_data.Post
import com.example.kotlin_social.android.common.fake_data.samplePosts
import com.example.kotlin_social.android.common.theming.DarkGray
import com.example.kotlin_social.android.common.theming.ExtraLargeSpacing
import com.example.kotlin_social.android.common.theming.LargeSpacing
import com.example.kotlin_social.android.common.theming.LightGray
import com.example.kotlin_social.android.common.theming.MediumSpacing
import com.example.kotlin_social.android.common.theming.SocialAppTheme

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: ((Post) -> Unit)? = null,
    onProfileClick: (userId: Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    maxLines: Int = Int.MAX_VALUE
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
            //.clickable { onPostClick(post) }
            .let { mod ->
                if (onPostClick != null) {
                    mod.clickable { onPostClick(post) }.padding(bottom = ExtraLargeSpacing)
                } else {
                    mod
                }
            }
    ) {
        PostHeader(
            name = post.authorName,
            profileUrl = post.imageUrl,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.id.toInt()) }
        )

        AsyncImage(
            model = post.imageUrl,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 1.0f)
                .padding(top = 10.dp),
            contentScale = ContentScale.Crop,
            placeholder = if (MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.light_image_place_holder)
            } else {
                painterResource(id = R.drawable.dark_image_place_holder)
            }
        )

        PostLikesRow(
            likesCount = post.likesCount,
            commentCount = post.commentCount,
            onLikeClick = { onLikeClick() },
            isPostLiked = post.isLiked,
            onCommentClick = { onCommentClick() }
        )

        Text(
            text = post.text,
            style = MaterialTheme.typography.body2,
            modifier = modifier.padding(horizontal = LargeSpacing)
                .padding(bottom = 10.dp),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun PostHeader(
    modifier: Modifier = Modifier,
    name: String,
    profileUrl: String?,
    date: String,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = LargeSpacing,
                vertical = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
            modifier = modifier.size(30.dp),
            url = profileUrl,
            onClick = onProfileClick
        )

        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )

        Box(
            modifier = modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(
                    color = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    }
                )
        )

        Text(
            text = date,
            style = MaterialTheme.typography.caption.copy(
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = if (MaterialTheme.colors.isLight) {
                    LightGray
                } else {
                    DarkGray
                }
            ),
            modifier = modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.round_more_horizontal),
            contentDescription = null,
            tint = if (MaterialTheme.colors.isLight) {
                LightGray
            } else {
                DarkGray
            }
        )
    }
}


@Composable
fun PostLikesRow(
    modifier: Modifier = Modifier,
    likesCount: Int,
    commentCount: Int,
    onLikeClick: () -> Unit,
    isPostLiked: Boolean,
    onCommentClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 0.dp,
                horizontal = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onLikeClick
        ) {
            Icon(
                painter = if (isPostLiked) {
                    painterResource(id = R.drawable.like_icon_filled)
                } else {
                    painterResource(id = R.drawable.like_icon_outlined)
                },
                contentDescription = null,
                tint = if (isPostLiked) Red else DarkGray
            )
        }

        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp)
        )

        Spacer(modifier = modifier.width(MediumSpacing))

        IconButton(
            onClick = onCommentClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chat_icon_outlined),
                contentDescription = null,
                tint = if (MaterialTheme.colors.isLight) {
                    LightGray
                } else {
                    DarkGray
                }
            )
        }

        Text(
            text = "$commentCount",
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp)
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PostListItemPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostListItem(
                post = samplePosts.first(),
                onPostClick = {},
                onProfileClick = {},
                onCommentClick = {},
                onLikeClick = {}
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PostHeaderPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostHeader(
                name = "Mr Dip",
                profileUrl = "",
                date = "20 min",
                onProfileClick = {}
            )
        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PostLikesRowPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostLikesRow(
                likesCount = 12,
                commentCount = 2,
                onLikeClick = {},
                isPostLiked = true,
                onCommentClick = {}
            )
        }
    }
}
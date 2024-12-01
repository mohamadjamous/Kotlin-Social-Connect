package com.example.kotlin_social.android.post

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigator
import com.example.kotlin_social.android.common.fake_data.Post
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PostDetail (navigator: DestinationsNavigator,
                postId: Post){
}
package com.example.kotlin_social.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kotlin_social.android.common.theming.SocialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
//        Firebase.initialize(this)
        setContent {
            SocialAppTheme {
               SocialApp()
            }
        }
    }
}


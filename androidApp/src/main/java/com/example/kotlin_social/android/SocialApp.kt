package com.example.kotlin_social.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_social.android.common.components.AppBar
import com.example.kotlin_social.android.common.datastore.UserSettings
import com.example.kotlin_social.android.destinations.HomeScreenDestination
import com.example.kotlin_social.android.destinations.LoginDestination
import com.example.kotlin_social.android.destinations.LoginScreenDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SocialApp(uid: String?) {

    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()

    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark) {
        MaterialTheme.colors.surface
    } else {
        MaterialTheme.colors.surface.copy(alpha = 0.95f)
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(modifier = Modifier, navHostController = navHostController)
        }

    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }


    LaunchedEffect(key1 = uid) {
        if (uid != null && uid.isEmpty()) {
            navHostController.navigate(LoginDestination.route) {
                popUpTo(HomeScreenDestination.route) {
                    inclusive = true
                }
            }
        }
    }

}
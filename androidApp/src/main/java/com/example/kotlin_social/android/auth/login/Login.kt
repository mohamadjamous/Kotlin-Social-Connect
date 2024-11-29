package com.example.kotlin_social.android.auth.login

import androidx.compose.runtime.Composable
import com.example.kotlin_social.android.destinations.HomeDestination
import com.example.kotlin_social.android.destinations.SignUpDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator
) {
    val viewModel: LoginViewModel = koinViewModel()

    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onNavigateToSignup = { navigator.navigate(SignUpDestination) },
        onNavigateToHome = { navigator.navigate(HomeDestination) })
}
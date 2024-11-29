package com.example.kotlin_social.android.auth.signup

import androidx.compose.runtime.Composable
import com.example.kotlin_social.android.auth.SignUpScreen
import com.example.kotlin_social.android.auth.SignUpViewModel
import com.example.kotlin_social.android.destinations.HomeDestination
import com.example.kotlin_social.android.destinations.LoginDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination()
@Composable
fun SignUp(
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = koinViewModel()

    SignUpScreen(
        uiState = viewModel.uiState,
        onUsernameChange = viewModel::updateUsername,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination)
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination)
        },
        onSignUpClick = viewModel::signup
    )
}

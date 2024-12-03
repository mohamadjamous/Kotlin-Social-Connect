package com.example.kotlin_social.android.account.edit

import androidx.compose.runtime.Composable
import androidx.navigation.Navigator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun EditProfile(navigator: DestinationsNavigator, userId: Int) {

    val viewModel: EditProfileViewModel = koinViewModel()

    EditProfileScreen(
        editProfileUiState = viewModel.uiState,
        onNameChange = viewModel::onNameChange,
        bioTextFieldValue = viewModel.bioTextFieldValue,
        onBioChange = viewModel::onBioChange,
        onUploadSucceed = { viewModel.uploadProfile() },
        userId = userId,
        onUiAction = { viewModel.fetchProfile(userId) }
    )

}
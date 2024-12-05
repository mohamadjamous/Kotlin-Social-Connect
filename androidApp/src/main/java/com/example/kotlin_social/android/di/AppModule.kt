package com.example.kotlin_social.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.kotlin_social.android.MainActivityViewModel
import com.example.kotlin_social.android.account.ProfileViewModel
import com.example.kotlin_social.android.account.edit.EditProfileViewModel
import com.example.kotlin_social.android.account.follows.FollowsViewModel
import com.example.kotlin_social.android.auth.SignUpViewModel
import com.example.kotlin_social.android.auth.login.LoginViewModel
import com.example.kotlin_social.common.data.UserSettingsSerializer
import com.example.kotlin_social.android.home.HomeScreenViewModel
import com.example.kotlin_social.android.post.PostDetailScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { PostDetailScreenViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { FollowsViewModel() }




}
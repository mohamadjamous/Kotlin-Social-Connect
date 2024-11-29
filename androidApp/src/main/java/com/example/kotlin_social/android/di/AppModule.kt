package com.example.kotlin_social.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.kotlin_social.android.MainActivityViewModel
import com.example.kotlin_social.android.auth.SignUpViewModel
import com.example.kotlin_social.android.auth.login.LoginViewModel
import com.example.kotlin_social.android.common.datastore.UserSettings
import com.example.kotlin_social.android.common.datastore.UserSettingsSerializer
import com.example.kotlin_social.android.home.HomeScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    
    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile("app_user_settings")
            }
        )
    }
}
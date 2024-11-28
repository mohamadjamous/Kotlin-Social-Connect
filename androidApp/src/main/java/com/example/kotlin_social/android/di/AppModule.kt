package com.example.kotlin_social.android.di

import com.example.kotlin_social.android.MainActivityViewModel
import com.example.kotlin_social.android.auth.SignUpViewModel
import com.example.kotlin_social.android.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel { LoginViewModel(get())}
    viewModel { SignUpViewModel(get())}
    viewModel { MainActivityViewModel(get())}
    
}
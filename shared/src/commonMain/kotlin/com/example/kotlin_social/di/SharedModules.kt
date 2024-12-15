package com.example.kotlin_social.di

import com.example.kotlin_social.auth.data.AuthRepositoryImpl
import com.example.kotlin_social.auth.data.AuthService
import com.example.kotlin_social.auth.domain.repository.AuthRepository
import com.example.kotlin_social.usecase.SignInUseCase
import com.example.kotlin_social.usecase.SignUpUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory {  }
}


fun getSharedModules() = listOf(platformModule, authModule, utilityModule)
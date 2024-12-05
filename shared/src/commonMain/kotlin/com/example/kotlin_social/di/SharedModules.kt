package com.example.kotlin_social.di

import org.koin.dsl.module

private val authModule = module {
//    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
//    factory { AuthService() }
//    factory { SignUpUseCase() }
//    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory {  }
}


fun getSharedModules() = listOf(platformModule, authModule, utilityModule)
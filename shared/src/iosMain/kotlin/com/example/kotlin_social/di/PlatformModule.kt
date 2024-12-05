package com.example.kotlin_social.di

import com.example.kotlin_social.common.data.IOSUserPreferences
import com.example.kotlin_social.common.data.createDatastore
import com.example.kotlin_social.common.data.local.UserPreferences
import org.koin.dsl.module

actual val platformModule = module {
    single<UserPreferences> { IOSUserPreferences(get()) }

    single {
        createDatastore()
    }
}
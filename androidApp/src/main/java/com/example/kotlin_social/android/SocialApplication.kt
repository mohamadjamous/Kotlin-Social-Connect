package com.example.kotlin_social.android

import android.app.Application
import com.example.kotlin_social.android.di.appModule
import org.koin.core.context.startKoin

class SocialApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}
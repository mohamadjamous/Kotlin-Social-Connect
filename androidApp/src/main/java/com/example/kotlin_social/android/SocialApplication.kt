package com.example.kotlin_social.android

import android.app.Application
import com.example.kotlin_social.android.di.appModule
import com.example.kotlin_social.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule + getSharedModules())
            androidContext(this@SocialApplication)
        }
    }
}
package com.example.kotlin_social.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.kotlin_social.common.data.AndroidUserPreferences
import com.example.kotlin_social.common.data.UserSettingsSerializer
import com.example.kotlin_social.common.data.local.PREFERENCES_FILE_NAME
import com.example.kotlin_social.common.data.local.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

//actual val platformModule = module {
//    single<UserPreferences> { AndroidUserPreferences(get()) }
//
//    single {
//        DataStoreFactory.create(
//            serializer = UserSettingsSerializer,
//            produceFile = {
//                androidContext().dataStoreFile(PREFERENCES_FILE_NAME)
//            }
//        )
//    }
//}
package com.example.kotlin_social.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.example.kotlin_social.android.common.datastore.UserSettings
import com.example.kotlin_social.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.map

class MainActivityViewModel(private val datastore: DataStore<UserSettings>) : ViewModel() {

    val authState = datastore.data.map { it.toAuthResultData().id }

}
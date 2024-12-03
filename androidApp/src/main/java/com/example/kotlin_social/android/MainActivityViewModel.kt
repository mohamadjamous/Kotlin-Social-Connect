package com.example.kotlin_social.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_social.android.common.datastore.UserSettings
import com.example.kotlin_social.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(private val datastore: DataStore<UserSettings>) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = datastore.data.map {
        MainActivityUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val currentUser: UserSettings) : MainActivityUiState
}
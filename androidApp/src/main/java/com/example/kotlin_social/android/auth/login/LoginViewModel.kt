package com.example.kotlin_social.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_social.common.data.local.UserSettings
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val datastore: DataStore<UserSettings>
) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    @OptIn(DelicateCoroutinesApi::class)
    fun signIn() {

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticating = true)

            if (uiState.email.isBlank() || uiState.email.length < 3) {
                uiState = uiState.copy(
                    isAuthenticating = false,
                    authErrorMessage = "Invalid Email or Password"
                )

            } else if (uiState.password.isBlank() || uiState.password.length < 3) {
                uiState = uiState.copy(
                    isAuthenticating = false,
                    authErrorMessage = "Invalid Email or Password"
                )
            } else {

                firebaseAuth.signInWithEmailAndPassword(uiState.email, uiState.password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) {
                            uiState = uiState.copy(
                                isAuthenticating = false,
                                authErrorMessage = it.exception?.message
                            )

                        } else {
                            uiState = uiState.copy(
                                isAuthenticating = false,
                                authenticationSucceed = true
                            )

                            GlobalScope.launch(Dispatchers.IO) {
                                datastore.updateData {
                                    UserSettings(
                                        id = firebaseAuth.currentUser!!.uid,
                                        name = "",
                                        bio = "",
                                        avatar = null,
                                        followersCount = 0,
                                        followingCount = 0
                                    )
                                }
                            }
                        }

                    }.addOnFailureListener {
                        uiState = uiState.copy(
                            isAuthenticating = false,
                            authErrorMessage = it.message
                        )
                    }
            }


        }
    }

    fun updateEmail(input: String) {
        uiState = uiState.copy(email = input)
    }

    fun updatePassword(input: String) {
        uiState = uiState.copy(password = input)
    }


}

data class LoginUiState(
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)



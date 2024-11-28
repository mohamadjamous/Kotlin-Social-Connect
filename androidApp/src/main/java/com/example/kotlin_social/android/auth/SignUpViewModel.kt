package com.example.kotlin_social.android.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_social.android.common.datastore.UserSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SignUpViewModel(private val dataStore: DataStore<UserSettings>) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set


    fun updateUsername(username: String) {
        uiState = uiState.copy(username = username)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }


    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun signup() {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance() // Initialize Firestore
        uiState = uiState.copy(isAuthenticating = true)



        viewModelScope.launch {

            if (uiState.username.isBlank() || uiState.username.length < 3) {
                uiState = uiState.copy(
                    isAuthenticating = false,
                    authErrorMessage = "Invalid name"
                )
            } else if (uiState.email.isBlank() || "@" !in uiState.email) {
                uiState = uiState.copy(
                    isAuthenticating = false,
                    authErrorMessage = "Invalid email"
                )
            } else if (uiState.password.isBlank() || uiState.password.length < 4) {
                uiState = uiState.copy(
                    isAuthenticating = false,
                    authErrorMessage = "Invalid password or too short!"
                )
            } else {

                firebaseAuth.createUserWithEmailAndPassword(uiState.email, uiState.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // User successfully created, now update the username
                            val user = firebaseAuth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(uiState.username)
                                .build()

                            user?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {

                                        println("User created successfully!")

                                        // Add user data to Firestore
                                        val userData = hashMapOf(
                                            "username" to uiState.username,
                                            "email" to uiState.email,
                                            "userId" to user.uid
                                        )

                                        firestore.collection("users")
                                            .document(user.uid)
                                            .set(userData)
                                            .addOnSuccessListener {
                                                println("User data added to Firestore successfully!")
                                                uiState = uiState.copy(
                                                    isAuthenticating = false,
                                                    authenticationSucceed = true
                                                )

                                                dataStore.updateData {

                                                }
                                            }
                                            .addOnFailureListener { e ->
                                                uiState = uiState.copy(
                                                    isAuthenticating = false,
                                                    authErrorMessage = task.exception?.message
                                                )
                                                println("Error adding user data to Firestore: ${e.message}")
                                            }

                                    } else {
                                        uiState = uiState.copy(
                                            isAuthenticating = false,
                                            authErrorMessage = updateTask.exception?.message
                                        )
                                        println("Error updating username: ${updateTask.exception?.message}")
                                    }
                                }
                        } else {
                            uiState = uiState.copy(
                                isAuthenticating = false,
                                authErrorMessage = task.exception?.message
                            )
                            println("Error creating user: ${task.exception?.message}")
                        }
                    }
            }
        }
    }


}



data class SignUpUiState(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)

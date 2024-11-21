//
//  SignInView.swift
//  iosApp
//
//  Created by Mohamad Jamous on 11/21/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var isAuthenticating: Bool = false
    @State private var authenticationSucceed: Bool = false
    @State private var authErrorMessage: String? = nil

    var onEmailChange: (String) -> Void = { _ in }
    var onPasswordChange: (String) -> Void = { _ in }
    var onNavigateToHome: () -> Void = {}
    var onSignInClick: () -> Void = {}
    var onNavigateToSignup: () -> Void = {}

    var body: some View {
        
        ZStack {
            VStack(spacing: 16) {
                ScrollView {
                    VStack(spacing: 24) {
                        // Email TextField
                        CustomTextField(
                            value: $email,
                            placeholder: "Enter your email",
                            keyboardType: .emailAddress
                        )
                        .onChange(of: email) { newValue in
                            onEmailChange(newValue)
                        }

                        // Password TextField
                        CustomTextField(
                            value: $password,
                            placeholder: "Enter your password",
                            isSecure: true
                        )
                        .onChange(of: password) { newValue in
                            onPasswordChange(newValue)
                        }

                        // Login Button
                        Button(action: {
                            isAuthenticating = true
                            onSignInClick()
                        }) {
                            Text("Log In")
                                .frame(maxWidth: .infinity)
                                .padding()
                                .foregroundColor(.white)
                                .background(Color.blue)
                                .cornerRadius(8)
                        }
                        .disabled(isAuthenticating)

                        // Navigate to Signup
                        GoToSignup(onNavigateToSignup: onNavigateToSignup)
                    }
                    .padding()
                }
            }
            .background(Color(UIColor.systemBackground))
            .ignoresSafeArea()
            .padding(.top, 5)

            // Loading Indicator
            if isAuthenticating {
                ProgressView()
            }
        }
        .onChange(of: authenticationSucceed) { success in
            if success {
                onNavigateToHome()
            }
        }
        .onChange(of: authErrorMessage) { error in
            if let error = error {
                // Display an error message
                print("Authentication error: \(error)")
            }
        }
    }
}

struct GoToSignup: View {
    var onNavigateToSignup: () -> Void

    var body: some View {
        HStack {
            Text("Don't have an account?")
                .foregroundColor(.gray)
            Button(action: onNavigateToSignup) {
                Text("Sign Up")
                    .foregroundColor(.blue)
                    .underline()
            }
        }
        .font(.footnote)
    }
}



struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}

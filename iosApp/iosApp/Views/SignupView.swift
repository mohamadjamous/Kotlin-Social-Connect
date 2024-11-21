//
//  SignupView.swift
//  iosApp
//
//  Created by Mohamad Jamous on 11/21/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SignupView: View {
    @State private var username: String = ""
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var isAuthenticating: Bool = false
    @State private var authenticationSucceed: Bool = false
    @State private var authErrorMessage: String? = nil
    @State private var path = NavigationPath()



    var body: some View {
        ZStack {
            ScrollView {
                VStack(spacing: 20) {
                    // Username TextField
                    CustomTextField(
                        value: $username,
                        placeholder: "Username",
                        keyboardType: .default
                    )
                    
                    // Email TextField
                    CustomTextField(
                        value: $email,
                        placeholder: "Email",
                        keyboardType: .emailAddress
                    )
                    
                    // Password TextField
                    CustomTextField(
                        value: $password,
                        placeholder: "Password",
                        keyboardType: .default,
                        isSecure: true
                    )
                    
                    // Sign Up Button
                    Button(action: {
                        isAuthenticating = true
                    }) {
                        Text("Sign Up")
                            .frame(maxWidth: .infinity)
                            .frame(height: 44)
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                    }
                    
                    // Navigate to Login
                    GoToLoginView {
                        path.append("LoginView")
                    }
                }
                .padding()
            }
            .background(Color(UIColor.systemBackground))
            .onAppear {
                if authenticationSucceed {
                    path.append("HomeView")
                }
                
                if let errorMessage = authErrorMessage {
                    // Show alert for the error message
                    print(errorMessage)
                }
            }
            .navigationDestination(for: String.self) { value in
                if value == "LoginView" {
                    LoginView()
                } else if value == "HomeView" {
                    HomeView()
                }
            }
            
            // Loading Indicator
            if isAuthenticating {
                ProgressView()
                    .scaleEffect(1.5)
            }
        }
    }
}





// Preview
struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignupView()
    }
}

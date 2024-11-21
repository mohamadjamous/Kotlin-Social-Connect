//
//  GoToLoginView.swift
//  iosApp
//
//  Created by Mohamad Jamous on 11/21/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct GoToLoginView: View {
    var onNavigateToLogin: () -> Void

    var body: some View {
        HStack(spacing: 5) {
            Text("Already have an account?")
                .font(.caption)
                .foregroundColor(.primary)
            
            Button(action: {
                onNavigateToLogin()
            }) {
                Text("Login")
                    .font(.caption)
                    .foregroundColor(.blue)
            }
        }
    }
}

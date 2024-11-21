//
//  CustomTextField.swift
//  iosApp
//
//  Created by Mohamad Jamous on 11/21/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CustomTextField: View {
    @Binding var value: String
    var placeholder: String
    var keyboardType: UIKeyboardType = .default
    var isSecure: Bool = false

    var body: some View {
        if isSecure {
            SecureField(placeholder, text: $value)
                .padding()
                .background(Color.gray.opacity(0.2))
                .cornerRadius(8)
        } else {
            TextField(placeholder, text: $value)
                .padding()
                .background(Color.gray.opacity(0.2))
                .cornerRadius(8)
                .keyboardType(keyboardType)
        }
    }
}

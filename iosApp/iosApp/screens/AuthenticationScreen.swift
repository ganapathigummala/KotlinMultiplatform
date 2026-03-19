// AuthenticationScreen.swift
import SwiftUI

struct AuthenticationScreen: View {
    @StateObject private var viewModel = AuthenticationViewModel()

    var body: some View {
        ScrollView {
            VStack(spacing: 24) {
                // Header
                HeaderSection()

                // Mobile Number Input
                MobileNumberInput(
                    countryCode: $viewModel.selectedCountryCode,
                    mobileNumber: $viewModel.mobileNumber,
                    error: viewModel.mobileNumberError
                )
                .onChange(of: viewModel.mobileNumber) { _ in
                    // Clear error when typing
                    viewModel.mobileNumberError = nil
                }

                // Consent Checkbox
                ConsentCheckbox(
                    isChecked: $viewModel.consentChecked,
                    error: viewModel.consentError
                )
                .onTapGesture {
                    viewModel.toggleConsent()
                    viewModel.consentError = nil
                }

                // Continue Button
                ContinueButton(
                    enabled: viewModel.isFormValid && !viewModel.isLoading,
                    isLoading: viewModel.isLoading,
                    action: viewModel.onContinue
                )

                // Alternative Sign-In Options
                AlternativeSignInOptions(
                    onGoogle: viewModel.onGoogleSignIn,
                    onEmailPassword: viewModel.onEmailPasswordLogin
                )

                // Login Link
                LoginLink(action: viewModel.onLoginLink)
            }
            .padding()
        }
    }
}
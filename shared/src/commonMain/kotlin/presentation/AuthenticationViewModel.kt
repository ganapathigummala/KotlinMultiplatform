package presentation

import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.AuthUiState

class AuthenticationViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(viewModelScope,AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun updateMobileNumber(number: String) {
        _uiState.update { it.copy(mobileNumber = number.filter { char -> char.isDigit() }) }
    }

    fun updateCountryCode(code: String) {
        _uiState.update { it.copy(selectedCountryCode = code) }
    }

    fun toggleConsent() {
        _uiState.update { it.copy(consentChecked = !it.consentChecked) }
    }

    fun onContinue() {
        if (!isFormValid()) return
        _uiState.update { it.copy(isLoading = true) }
        // Simulate OTP initiation – in real app, call shared method
        // After success/failure, update loading and navigate
    }

    fun onGoogleSignIn() {
        // Trigger Google Sign-In flow
    }

    fun onEmailPasswordLogin() {
        // Navigate to email/password screen
    }

    fun onLoginLink() {
        // Navigate to login screen (if different from email/password)
    }

    fun isFormValid(): Boolean {
        return uiState.value.mobileNumber.length == 10 && uiState.value.consentChecked
    }
}
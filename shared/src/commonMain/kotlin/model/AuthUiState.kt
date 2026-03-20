package model

import kotlinx.serialization.Serializable

@Serializable
data class AuthUiState(
    val selectedCountryCode: String = "+1",
    val mobileNumber: String = "",
    val consentChecked: Boolean = false,
    val isLoading: Boolean = false,
    val mobileNumberError: String? = null,
    val consentError: String? = null
)
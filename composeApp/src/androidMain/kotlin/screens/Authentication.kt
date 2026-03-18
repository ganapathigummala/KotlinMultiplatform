package screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import authentication.AlternativeSignInOptions
import authentication.ConsentCheckbox
import authentication.ContinueButton
import authentication.HeaderSection
import authentication.LoginLink
import authentication.MobileNumberInput
import com.example.multimodule.R
import viewmodel.AuthenticationViewModel
@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
            colorResource(R.color.background)
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        HeaderSection()

        Spacer(modifier = Modifier.height(32.dp))

        // Mobile Input with Country Code
        MobileNumberInput(
            countryCode = uiState.selectedCountryCode,
            mobileNumber = uiState.mobileNumber,
            onCountryCodeChange = { viewModel.updateCountryCode(it) },
            onMobileNumberChange = { viewModel.updateMobileNumber(it) },
            error = uiState.mobileNumberError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Consent Checkbox
        ConsentCheckbox(
            checked = uiState.consentChecked,
            onCheckedChange = { viewModel.toggleConsent() },
            error = uiState.consentError
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        ContinueButton(
            enabled = viewModel.isFormValid() && !uiState.isLoading,
            isLoading = uiState.isLoading,
            onClick = { viewModel.onContinue() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Alternative sign-in options
        AlternativeSignInOptions(
            onGoogleClick = { viewModel.onGoogleSignIn() },
            onEmailPasswordClick = { viewModel.onEmailPasswordLogin() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation link
        LoginLink(onClick = { viewModel.onLoginLink() })
    }
}
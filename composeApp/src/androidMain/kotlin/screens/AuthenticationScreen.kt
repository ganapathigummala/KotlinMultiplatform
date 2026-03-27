package screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.multimodule.R
import presentation.SharedViewModel
import org.koin.compose.viewmodel.koinViewModel
import uiComponents.authentication.AlternativeSignInOptions
import uiComponents.authentication.ConsentCheckbox
import uiComponents.authentication.ContinueButton
import uiComponents.authentication.InfiniteHorizontalCarousel
import uiComponents.authentication.LoginLink
import uiComponents.authentication.MobileNumberInput
import presentation.AuthenticationViewModel
@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val uiData by sharedViewModel.uiData.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageUrls = listOf(
        "https://picsum.photos/800/400?1",
        "https://picsum.photos/800/400?2",
        "https://picsum.photos/800/400?3",
        "https://picsum.photos/800/400?4"
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(R.color.background))
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            InfiniteHorizontalCarousel(
                items = imageUrls,
                itemWidth = screenWidth * 0.7f,
                itemSpacing = 10.dp,
                contentPadding = PaddingValues(horizontal = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 12.dp)
            ) { imageUrl ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.bitcoin)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
//        HeaderSection()
//        Text(uiData)
//        Spacer(modifier = Modifier.height(32.dp))

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
            onClick = { onLoginSuccess() }
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
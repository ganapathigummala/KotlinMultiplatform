package screens

import androidx.compose.runtime.*
import network.NetworkError
import presentation.ApodViewModel
import org.koin.compose.viewmodel.koinViewModel
import state.Resource

@Composable
fun HomeScreen(
    viewModel: ApodViewModel = koinViewModel(),
    onChat: (String, Int) -> Unit = { _, _ -> }
){

    val state by viewModel.apodState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodayApod()
    }

    when (val result = state) {

        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success -> {
            result.data?.let {

                ApodContent(
                    data = it,
                    onChat = onChat
                )

            }
        }

        is Resource.Error -> {

            val message = when (result.error) {

                NetworkError.NoInternet -> "No internet connection"
                NetworkError.Timeout -> "Request timeout"
                NetworkError.Unauthorized -> "Unauthorized"
                NetworkError.ServerError -> "Server error"
                else -> "Something went wrong"
            }

            ErrorScreen(message)
        }
    }
}
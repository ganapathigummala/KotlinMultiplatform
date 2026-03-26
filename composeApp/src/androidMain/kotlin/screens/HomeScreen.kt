package screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import logger.Logger
import viewmodels.ApodViewModel
import state.Resource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: ApodViewModel = koinViewModel(),
) {

    val state by viewModel.apodState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadTodayApod()
    }
    when (val result = state) {

        is Resource.Loading -> {
            Logger.d("gana", "Loading APOD")
            LoadingScreen()
        }

        is Resource.Success -> {
            result.data?.let {
                ApodContent(data = it)
            }
        }

        is Resource.Error -> {
            Logger.d("gana", "Error: ${result.message}")
            ErrorScreen(result.message)
        }
    }
}
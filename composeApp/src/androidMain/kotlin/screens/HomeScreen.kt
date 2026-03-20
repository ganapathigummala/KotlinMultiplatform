package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import viewmodels.ApodViewModel
import state.Resource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: ApodViewModel = koinViewModel(), modifier: Modifier = Modifier) {
    val state by viewModel.apodState.collectAsState()
    println("🏠 UI state: $state")   // Debugging log

    LaunchedEffect(Unit) {
        viewModel.loadTodayApod()
    }

    when (val apod = state) {
        is Resource.Loading -> Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        is Resource.Success -> {
            println("apod ${apod.data?.title}")

            if (apod != null) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp), // Padding inside the scrollable column
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    apod.data?.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    apod.data?.date?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    if (apod.data?.media_type == "image") {
                        val imageUrl = apod.data?.hdurl ?: apod.data?.url

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = apod.data?.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            onSuccess = { println("✅ Image loaded") },
                            onError = { println("❌ Image failed: ${it.result.throwable}") }
                        )
                    } else {
                        Text("Video content - open in browser")
                    }

                    apod.data?.explanation?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    apod.data?.copyright?.let {
                        Text(
                            text = "© $it",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            } else {
                // No APOD available
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("No APOD available today")
                }
            }
        }

        is Resource.Error -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${apod.message}")
            }
        }
    }
}
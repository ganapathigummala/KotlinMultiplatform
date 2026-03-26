package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.multimodule.coreNetwork.model.ApodResponse

@Composable
fun ApodContent(
    data: ApodResponse,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = data.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = data.date,
            style = MaterialTheme.typography.bodyMedium
        )

        if (data.media_type == "image") {

            val imageUrl = data.hdurl ?: data.url

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = data.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        } else {
            Text("Video content - open in browser")
        }

        Text(
            text = data.explanation,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        data.copyright?.let {
            Text(
                text = "© $it",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
package uiComponents.authentication

import android.R.attr.scaleY
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun <T> InfiniteHorizontalCarousel(
    items: List<T>,
    itemWidth: Dp,
    itemSpacing: Dp,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    if (items.isEmpty()) return

    val repeatedCount = 1000
    val startIndex = (repeatedCount / 2) - ((repeatedCount / 2) % items.size)

    val state = rememberPagerState(initialPage = startIndex) { repeatedCount }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            state.animateScrollToPage(state.currentPage + 1)
        }
    }

    Box(modifier = modifier) {
        HorizontalPager(
            state = state,
            pageSize = PageSize.Fixed(itemWidth),
            contentPadding = PaddingValues(horizontal = 42.dp),
            pageSpacing = itemSpacing,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            val actualIndex = index % items.size
            val item = items[actualIndex]

            Box(
                modifier = Modifier
                    .width(itemWidth)
                    .graphicsLayer {
                        val pageOffset =
                            ((state.currentPage - index) + state.currentPageOffsetFraction).absoluteValue

                        // Add a subtle scale effect
                        scaleY = lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                itemContent(item)
            }
        }
    }
}
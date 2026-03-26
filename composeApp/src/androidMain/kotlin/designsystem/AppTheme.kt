package designsystem


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

private val LightColors = lightColorScheme(

    primary = AppColors.Primary500,
    onPrimary = AppColors.Primary50,

    secondary = AppColors.Secondary500,
    onSecondary = AppColors.Primary50,

    tertiary = AppColors.Tertiary500,

    error = AppColors.Error500,

    background = AppColors.Primary50,
    surface = AppColors.Primary50,

    onBackground = AppColors.Primary900,
    onSurface = AppColors.Primary900
)

private val DarkColors = darkColorScheme(

    primary = AppColors.Primary300,
    onPrimary = AppColors.Primary900,

    secondary = AppColors.Secondary300,
    onSecondary = AppColors.Primary900,

    tertiary = AppColors.Tertiary300,

    error = AppColors.Error500,

    background = AppColors.Primary900,
    surface = AppColors.Primary800,

    onBackground = AppColors.Primary50,
    onSurface = AppColors.Primary50
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
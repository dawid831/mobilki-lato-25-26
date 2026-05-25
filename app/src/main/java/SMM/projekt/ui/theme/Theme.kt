package SMM.projekt.ui.theme

import SMM.projekt.utils.AppFont
import SMM.projekt.utils.SettingsUiState
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyApplicationTheme(
    settings: SettingsUiState,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (settings.darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        settings.darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val fontFamily = when(settings.selectedFont) {
        AppFont.Default -> RobotoFont
        AppFont.Serif -> SerifFont
        AppFont.Monospace -> MonoFont
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography(
            fontScale = settings.fontScale,
            fontFamily = fontFamily
        ),
        content = content
    )
}
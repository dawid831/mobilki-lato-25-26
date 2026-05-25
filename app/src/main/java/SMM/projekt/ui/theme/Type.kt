package SMM.projekt.ui.theme

import SMM.projekt.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val RobotoFont = FontFamily(
    Font(R.font.roboto_regular)
)

val SerifFont = FontFamily(
    Font(R.font.merriweather_regular)
)

val MonoFont = FontFamily(
    Font(R.font.jetbrainsmono_regular)
)

fun appTypography(
    fontScale: Float,
    fontFamily: FontFamily
): Typography {

    val base = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal
    )

    return Typography(
        headlineMedium = base.copy(fontSize = (28 * fontScale).sp),
        headlineSmall = base.copy(fontSize = (24 * fontScale).sp),

        titleLarge = base.copy(fontSize = (22 * fontScale).sp),
        titleMedium = base.copy(fontSize = (18 * fontScale).sp),
        titleSmall = base.copy(fontSize = (14 * fontScale).sp),

        bodyLarge = base.copy(fontSize = (16 * fontScale).sp),
        bodyMedium = base.copy(fontSize = (14 * fontScale).sp),
        bodySmall = base.copy(fontSize = (12 * fontScale).sp),

        labelLarge = base.copy(fontSize = (14 * fontScale).sp),
        labelMedium = base.copy(fontSize = (12 * fontScale).sp),
        labelSmall = base.copy(fontSize = (11 * fontScale).sp)
    )
}
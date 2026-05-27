package SMM.projekt.utils

data class SettingsUiState(
    val darkTheme: Boolean = false,
    val fontScale: Float = 1f,
    val selectedFont: AppFont = AppFont.Default,
    val isMuted: Boolean = false,
    val volume: Float = 0.3f
)

enum class AppFont {
    Default,
    Serif,
    Monospace
}

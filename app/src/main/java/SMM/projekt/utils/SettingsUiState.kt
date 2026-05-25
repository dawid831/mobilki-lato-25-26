package SMM.projekt.utils

data class SettingsUiState(
    val darkTheme: Boolean = false,
    val fontScale: Float = 1f,
    val selectedFont: AppFont = AppFont.Default
)

enum class AppFont {
    Default,
    Serif,
    Monospace
}

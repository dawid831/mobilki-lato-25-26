package SMM.projekt.ui.screens

import SMM.projekt.ui.theme.MonoFont
import SMM.projekt.ui.theme.RobotoFont
import SMM.projekt.ui.theme.SerifFont
import SMM.projekt.utils.AppFont
import SMM.projekt.utils.SettingsUiState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settings: SettingsUiState,
    onSettingsChange: (SettingsUiState) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = { Text("Ustawienia", style = MaterialTheme.typography.headlineMedium) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Wróć"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Switch(
                checked = settings.darkTheme,
                onCheckedChange = {

                    onSettingsChange(
                        settings.copy(
                            darkTheme = it
                        )
                    )
                }
            )

            Slider(
                value = settings.fontScale,
                onValueChange = {
                    onSettingsChange(
                        settings.copy(
                            fontScale = it
                        )
                    )
                },
                valueRange = 0.8f..1.5f
            )

            Column {
                AppFont.entries.forEach { font ->
                    Row {
                        RadioButton(
                            selected =
                                settings.selectedFont == font,
                            onClick = {
                                onSettingsChange(
                                    settings.copy(
                                        selectedFont = font
                                    )
                                )
                            }
                        )

                        Text(
                            text = font.name,

                            style = MaterialTheme.typography.bodyLarge,

                            fontFamily = when(font) {
                                AppFont.Default -> RobotoFont
                                AppFont.Serif -> SerifFont
                                AppFont.Monospace -> MonoFont
                            }
                        )
                    }
                }
            }
        }
    }
}
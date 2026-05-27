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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material.icons.filled.DarkMode

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
                .verticalScroll(rememberScrollState())
        ) {
            Row {
                Icon(
                    imageVector = Icons.Filled.DarkMode,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp)
                )

                Text(
                    text = "Tryb nocny",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge
                )

                Switch(
                    checked = settings.darkTheme,
                    onCheckedChange = {
                        onSettingsChange(settings.copy(darkTheme = it))
                    }
                )
            }

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

            Text(
                text = "Dźwięk",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 24.dp)
            )

            Row {
                Text(
                    text = "Wycisz muzykę",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge
                )

                Switch(
                    checked = settings.isMuted,
                    onCheckedChange = {
                        onSettingsChange(
                            settings.copy(isMuted = it)
                        )
                    }
                )
            }

            Text(
                text = "Głośność: ${(settings.volume * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )

            Slider(
                value = settings.volume,
                onValueChange = {
                    onSettingsChange(
                        settings.copy(volume = it)
                    )
                },
                valueRange = 0f..1f,
                enabled = !settings.isMuted
            )
        }
    }
}
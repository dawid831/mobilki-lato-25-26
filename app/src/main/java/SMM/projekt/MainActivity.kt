    package SMM.projekt

    import SMM.projekt.navigation.AppNavGraph
    import SMM.projekt.ui.theme.MyApplicationTheme
    import SMM.projekt.utils.AppFont
    import SMM.projekt.utils.AudioManager
    import SMM.projekt.utils.SettingsUiState
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.saveable.mapSaver
    import androidx.compose.runtime.saveable.rememberSaveable
    import androidx.compose.runtime.setValue

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            AudioManager.start(this)

            val settingsSaver = mapSaver(
                save = {
                    mapOf(
                        "darkTheme" to it.darkTheme,
                        "fontScale" to it.fontScale,
                        "selectedFont" to it.selectedFont.name,
                        "isMuted" to it.isMuted,
                        "volume" to it.volume
                    )
                },
                restore = {
                    SettingsUiState(
                        darkTheme = it["darkTheme"] as Boolean,
                        fontScale = it["fontScale"] as Float,
                        selectedFont = AppFont.valueOf(it["selectedFont"] as String),
                        isMuted = it["isMuted"] as Boolean,
                        volume = it["volume"] as Float
                    )
                }
            )

            setContent {
                var settings by rememberSaveable(stateSaver = settingsSaver) {
                    mutableStateOf(SettingsUiState())
                }

                LaunchedEffect(settings.isMuted, settings.volume) {
                    AudioManager.setMuted(settings.isMuted)
                    AudioManager.setVolume(settings.volume)
                }

                MyApplicationTheme(settings = settings) {
                    AppNavGraph(
                        settings = settings,
                        onSettingsChange = {
                            settings = it
                        }
                    )
                }
            }
        }

        override fun onDestroy() {
            super.onDestroy()

            if (!isChangingConfigurations) {
                AudioManager.stop()
            }
        }

        override fun onPause() {
            super.onPause()
            AudioManager.pause()
        }

        override fun onResume() {
            super.onResume()
            AudioManager.resume()
        }
    }

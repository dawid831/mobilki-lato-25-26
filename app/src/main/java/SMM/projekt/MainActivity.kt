package SMM.projekt

import SMM.projekt.navigation.AppNavGraph
import SMM.projekt.ui.theme.MyApplicationTheme
import SMM.projekt.utils.AudioManager
import SMM.projekt.utils.SettingsUiState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AudioManager.start(this)

        setContent {
            var settings by remember {
                mutableStateOf(SettingsUiState())
            }

            MyApplicationTheme(
                settings = settings,
            ) {

                AppNavGraph(
                    settings = settings,
                    onSettingsChange  = {
                        settings = it
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AudioManager.stop()
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

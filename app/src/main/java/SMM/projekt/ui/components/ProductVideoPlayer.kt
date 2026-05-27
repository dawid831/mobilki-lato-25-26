package SMM.projekt.ui.components

import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun ProductVideoPlayer(
    videoRes: Int,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        factory = { context ->
            VideoView(context).apply {
                val uri = Uri.parse("android.resource://${context.packageName}/$videoRes")

                setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                    start()
                }

                setOnErrorListener { _, what, extra ->
                    Log.e("ProductVideo", "Video error: what=$what extra=$extra res=$videoRes")
                    true
                }

                setVideoURI(uri)
            }
        }
    )
}
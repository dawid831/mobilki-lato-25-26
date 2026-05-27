package SMM.projekt.ui.components

import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ProductVideoPlayer(
    videoRes: Int,
    modifier: Modifier = Modifier,
    height: Dp = 240.dp,
    onClick: () -> Unit = {}
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clickable { onClick() },
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
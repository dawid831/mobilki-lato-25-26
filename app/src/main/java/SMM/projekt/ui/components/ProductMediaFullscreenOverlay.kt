package SMM.projekt.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductMediaFullscreenOverlay(
    mediaType: ProductMediaType,
    imageRes: Int,
    videoRes: Int,
    productName: String,
    onDismiss: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    val customEasing = CubicBezierEasing(
        0.25f,
        0.1f,
        0.25f,
        1f
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.82f,
        animationSpec = tween(
            durationMillis = 550,
            easing = customEasing
        ),
        label = "fullscreenScale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 550,
            easing = customEasing
        ),
        label = "fullscreenAlpha"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (visible) {
            MaterialTheme.colorScheme.scrim.copy(alpha = 0.88f)
        } else {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        },
        animationSpec = tween(
            durationMillis = 550,
            easing = customEasing
        ),
        label = "fullscreenBackground"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable { onDismiss() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        when (mediaType) {
            ProductMediaType.Image -> {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        },
                    contentScale = ContentScale.Fit
                )
            }

            ProductMediaType.Video -> {
                ProductVideoPlayer(
                    videoRes = videoRes,
                    height = 520.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                )
            }
        }
    }
}
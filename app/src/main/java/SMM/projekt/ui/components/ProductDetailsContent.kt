package SMM.projekt.ui.components

import SMM.projekt.data.Product
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


enum class ProductMediaType {
    Image,
    Video
}

@Composable
fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier,
    onMediaClick: (ProductMediaType) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var selectedMedia by remember {
        mutableStateOf<ProductMediaType?>(null)
    }

    if (isLandscape) {
        ProductDetailsLandscapeContent(
            product = product,
            modifier = modifier,
            onMediaClick = onMediaClick
        )
    } else {
        ProductDetailsPortraitContent(
            product = product,
            modifier = modifier,
            onMediaClick = onMediaClick
        )
    }
}

@Composable
private fun ProductDetailsPortraitContent(
    product: Product,
    modifier: Modifier = Modifier,
    onMediaClick: (ProductMediaType) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ProductMediaCard(
            imageRes = product.imageRes,
            productName = product.name,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onMediaClick(ProductMediaType.Image)
            }
        )

        ProductInfo(product)

        ProductVideoPlayer(
            videoRes = product.videoRes,
            onClick = {
                onMediaClick(ProductMediaType.Video)
            }
        )
    }
}

@Composable
private fun ProductDetailsLandscapeContent(
    product: Product,
    modifier: Modifier = Modifier,
    onMediaClick: (ProductMediaType) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductMediaCard(
                imageRes = product.imageRes,
                productName = product.name,
                modifier = Modifier.weight(1f),
                onClick = {
                    onMediaClick(ProductMediaType.Image)
                }
            )

            ProductVideoPlayer(
                videoRes = product.videoRes,
                modifier = Modifier.weight(1f),
                onClick = {
                    onMediaClick(ProductMediaType.Video)
                }
            )
        }

        ProductInfo(product)
    }
}

@Composable
fun ProductInfo(product: Product) {
    Text(product.name, style = MaterialTheme.typography.headlineMedium)

    Text(
        text = product.price,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )

    Text(
        text = product.description,
        style = MaterialTheme.typography.bodyLarge
    )
}
package SMM.projekt.ui.components

import SMM.projekt.data.Product
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier
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
            onVideoClick = {}
        )

        Text(product.name, style = MaterialTheme.typography.headlineMedium)

        Text(
            text = product.price,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(product.description, style = MaterialTheme.typography.bodyLarge)

        ProductVideoPlayer(videoRes = product.videoRes)
    }
}
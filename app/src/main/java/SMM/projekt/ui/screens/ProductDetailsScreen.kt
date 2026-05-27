package SMM.projekt.ui.screens

import SMM.projekt.data.Product
import SMM.projekt.ui.components.ProductDetailsContent
import SMM.projekt.ui.components.ProductMediaFullscreenOverlay
import SMM.projekt.ui.components.ProductMediaType
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: Product,
    onBackClick: () -> Unit
) {
    var selectedMedia by remember {
        mutableStateOf<ProductMediaType?>(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Wróć do listy") },
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
            ProductDetailsContent(
                product = product,
                modifier = Modifier.padding(paddingValues),
                onMediaClick = {
                    selectedMedia = it
                }
            )
        }

        selectedMedia?.let { mediaType ->
            ProductMediaFullscreenOverlay(
                mediaType = mediaType,
                imageRes = product.imageRes,
                videoRes = product.videoRes,
                productName = product.name,
                onDismiss = {
                    selectedMedia = null
                }
            )
        }
    }
}
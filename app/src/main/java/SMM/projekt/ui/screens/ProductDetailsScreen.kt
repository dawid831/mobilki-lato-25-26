package SMM.projekt.ui.screens

import SMM.projekt.data.productDataList
import SMM.projekt.ui.components.ProductMediaCard
import SMM.projekt.utils.getDrawableIdByName
import SMM.projekt.utils.getRawIdByName
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    productId: Int?,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val product = productDataList.find {
        it.id == productId
    }

    if (product == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Nie znaleziono produktu") },
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
            Text(
                text = "Produkt nie istnieje.",
                modifier = Modifier.padding(paddingValues).padding(24.dp)
            )
        }

        return
    }

    val imageRes = getDrawableIdByName(
        context = context,
        name = product.key
    )

    val videoRes = getRawIdByName(
        context = context,
        name = "${product.key}_video"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(product.name)
                },
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
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ProductMediaCard(
                imageRes = imageRes,
                productName = product.name,
                onVideoClick = {
                    // tutaj później odpalisz fullscreen video
                    // na razie można zostawić puste
                }
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = product.price,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "ID produktu: ${product.id}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
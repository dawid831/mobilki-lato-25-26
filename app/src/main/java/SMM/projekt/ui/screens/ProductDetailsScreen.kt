package SMM.projekt.ui.screens

import SMM.projekt.data.productDataList
import SMM.projekt.ui.components.ProductDetailsContent
import androidx.compose.foundation.layout.*
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
        ProductDetailsContent(product, Modifier.padding(paddingValues))
    }
}
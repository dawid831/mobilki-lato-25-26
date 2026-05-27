package SMM.projekt.ui.screens

import SMM.projekt.data.Category
import SMM.projekt.data.Product
import SMM.projekt.ui.components.ProductCarouselCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    categories: List<Category>,
    products: List<Product>,
    onCategoryClick: (Int) -> Unit,
    onProductClick: (Int) -> Unit,
    onThemeClick: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val isLandscape = maxWidth > maxHeight

            if (isLandscape) {
                HomeLandscapeContent(
                    categories = categories,
                    products = products,
                    onCategoryClick = onCategoryClick,
                    onProductClick = onProductClick,
                    onThemeClick = onThemeClick
                )
            } else {
                HomePortraitContent(
                    categories = categories,
                    products = products,
                    onCategoryClick = onCategoryClick,
                    onProductClick = onProductClick,
                    onThemeClick = onThemeClick
                )
            }
        }
    }
}

@Composable
fun HomePortraitContent(
    categories: List<Category>,
    products: List<Product>,
    onCategoryClick: (Int) -> Unit,
    onProductClick: (Int) -> Unit,
    onThemeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onThemeClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Ustawienia"
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Witaj w MyDesk",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Wybierz sprzęt do podglądu",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(40.dp))

        categories.forEach { category ->
            Button(
                onClick = { onCategoryClick(category.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp)
                    .testTag("category_${category.id}"),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Karuzela galerii:",
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCarouselCard(
                    product = product,
                    onClick = { onProductClick(product.id) }
                )
            }
        }
    }
}

@Composable
fun HomeLandscapeContent(
    categories: List<Category>,
    products: List<Product>,
    onCategoryClick: (Int) -> Unit,
    onProductClick: (Int) -> Unit,
    onThemeClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Witaj w MyDesk",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(16.dp))

            categories.forEach { category ->
                Button(
                    onClick = { onCategoryClick(category.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 52.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    )
                ) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2
                    )
                }

                Spacer(Modifier.height(12.dp))
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            IconButton(onClick = onThemeClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Ustawienia"
                )
            }

            Text("Karuzela galerii:", style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products) { product ->
                    ProductCarouselCard (
                        product = product,
                        onClick = { onProductClick(product.id) }
                    )
                }
            }
        }
    }
}


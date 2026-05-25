package SMM.projekt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import SMM.projekt.data.Category
import SMM.projekt.data.Product
import SMM.projekt.utils.getDrawableIdByName
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.platform.LocalContext

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
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(category.name, style = MaterialTheme.typography.titleLarge)
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
                HomeCarouselItem(
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(category.name, style = MaterialTheme.typography.titleLarge)
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
                    HomeCarouselItem(
                        product = product,
                        onClick = { onProductClick(product.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeCarouselItem(
    product: Product,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .size(width = 115.dp, height = 145.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp)
    ) {
        Image(
            painter = painterResource(id = getDrawableIdByName(
                    context = context,
                    name = "${product.key}_thumb"
                )
            ),
            contentDescription = product.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
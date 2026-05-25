package SMM.projekt.ui.components

import SMM.projekt.data.Product
import SMM.projekt.utils.getDrawableIdByName
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ProductCarouselCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            val context = LocalContext.current
            Image(
                painter = painterResource(id = getDrawableIdByName(
                    context = context,
                    name = "${product.key}_thumb"
                )),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(18.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = product.categoryId.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
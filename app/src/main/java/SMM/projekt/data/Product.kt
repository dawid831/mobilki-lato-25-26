package SMM.projekt.data

data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageRes: Int,
    val iconRes: Int,
    val videoRes: Int
)

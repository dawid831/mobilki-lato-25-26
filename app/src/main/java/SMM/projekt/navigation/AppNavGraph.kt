package SMM.projekt.navigation

import SMM.projekt.data.categoryDataList
import SMM.projekt.data.productDataList
import SMM.projekt.ui.screens.CategoryScreen
import SMM.projekt.ui.screens.HomeScreen
import SMM.projekt.ui.screens.ProductDetailsScreen
import SMM.projekt.ui.screens.SettingsScreen
import SMM.projekt.utils.SettingsUiState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(
    settings: SettingsUiState,
    onSettingsChange: (SettingsUiState) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                categories = categoryDataList,
                products = productDataList,

                onCategoryClick = { categoryId ->
                    navController.navigate("category/$categoryId")
                },
                onProductClick = { productId ->
                    navController.navigate("product/$productId")
                },
                onThemeClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments
                ?.getString("productId")
                ?.toIntOrNull()

            ProductDetailsScreen(
                productId = productId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("category/{categoryId}") { backStackEntry ->

            val categoryId = backStackEntry.arguments
                ?.getString("categoryId")
                ?.toIntOrNull()

            val category = categoryDataList.find {
                it.id == categoryId
            }

            val products = productDataList.filter {
                it.categoryId == categoryId
            }

            CategoryScreen(
                categoryName = category?.name ?: "Kategoria",
                products = products,
                onProductClick = { productId ->
                    navController.navigate("product/$productId")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("settings") {
            SettingsScreen(
                settings = settings,
                onSettingsChange = onSettingsChange,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
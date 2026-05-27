package SMM.projekt.navigation

import SMM.projekt.data.categoryDataList
import SMM.projekt.data.productDataList
import SMM.projekt.ui.screens.CategoryScreen
import SMM.projekt.ui.screens.ErrorScreen
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

            val product = productDataList.find {
                it.id == productId
            }

            if (product == null) {
                ErrorScreen(
                    errorMessage = "Nie odnaleziono produktu o id $productId",
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            } else {
                ProductDetailsScreen(
                    product = product,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
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

            if (category == null) {
                ErrorScreen(
                    errorMessage = "Nie odnalezeiono kategorii o id $categoryId",
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            } else {
                CategoryScreen(
                    category = category,
                    products = products,
                    onProductClick = { productId ->
                        navController.navigate("product/$productId")
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
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
package com.sagarika.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sagarika.features.presentation.composables.ProductDetailScreen
import com.sagarika.features.presentation.composables.ProductListScreen
import com.sagarika.features.presentation.viewmodels.ProductDetailsViewModel
import com.sagarika.features.presentation.viewmodels.ProductListViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    productListViewModel: ProductListViewModel = hiltViewModel(),
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ProductList.route
    )
    {
        composable(route = Screens.ProductList.route) {
            ProductListScreen(navController, productListViewModel)
        }

        composable(
            Screens.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                },


                )
        ) {

            val productId = remember {
                it.arguments?.getString("productId")
            }

            ProductDetailScreen(
                productId = productId!!,
                navController = navController,
                productDetailsViewModel
            )
        }
    }
}


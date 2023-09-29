package com.sagarika.features.ecommerce.presentation.navigation


sealed class Screens(val route: String) {
    object ProductList: Screens("product_list_screen")
    object ProductDetail: Screens("product_detail_screen")
 }
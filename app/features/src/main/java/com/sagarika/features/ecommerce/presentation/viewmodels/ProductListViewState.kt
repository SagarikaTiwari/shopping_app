package com.sagarika.features.ecommerce.presentation.viewmodels


sealed class ProductListViewState {
    object Loading : ProductListViewState()
    object Error : ProductListViewState()
    data class Content(val productList: List<ProductCardViewState>) : ProductListViewState()
}


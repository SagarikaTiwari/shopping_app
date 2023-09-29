package com.sagarika.features.ecommerce.presentation.viewmodels

import com.sagarika.domain.entities.ProductDetails


sealed class ProductDetailsViewState {
    object Loading : ProductDetailsViewState()
    data class Content(val product: ProductDetails) : ProductDetailsViewState()
    object Error : ProductDetailsViewState()
}
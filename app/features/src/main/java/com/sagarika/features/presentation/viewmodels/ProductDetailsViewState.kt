package com.sagarika.features.presentation.viewmodels

import com.sagarika.domain.entities.ProductDetails


sealed class ProductDetailsViewState {
    object Loading : ProductDetailsViewState()
    data class Content(val product: ProductDetails) : ProductDetailsViewState()
    object Error : ProductDetailsViewState()
}
package com.sagarika.features.ecommerce.presentation.viewmodels
data class ProductCardViewState(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)
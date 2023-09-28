package com.sagarika.features.presentation.mapper

import com.sagarika.features.presentation.viewmodels.ProductCardViewState
import com.sagarika.domain.entities.Product
import javax.inject.Inject

class ProductToProductCardViewStateMapper @Inject constructor(
) {
    suspend fun mapProductToProductCardView(product: Product): ProductCardViewState {
        with(product) {
            return ProductCardViewState(
                productId,
                title,
                description,
                "INR $price",
                imageUrl,
                false,
            )
        }
    }
}
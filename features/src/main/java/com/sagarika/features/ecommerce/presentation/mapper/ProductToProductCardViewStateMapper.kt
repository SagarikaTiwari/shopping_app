package com.sagarika.features.ecommerce.presentation.mapper

import com.sagarika.features.ecommerce.presentation.viewmodels.ProductCardViewState
import com.sagarika.domain.entities.Product
import javax.inject.Inject

class ProductToProductCardViewStateMapper @Inject constructor(
) {
    fun mapProductToProductCardView(product: Product): ProductCardViewState {
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
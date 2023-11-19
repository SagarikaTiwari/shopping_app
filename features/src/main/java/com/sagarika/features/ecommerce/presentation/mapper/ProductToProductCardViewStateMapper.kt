package com.sagarika.features.ecommerce.presentation.mapper

import com.sagarika.features.ecommerce.presentation.viewmodels.ProductCardViewState
import com.sagarika.data.entities.Product
import javax.inject.Inject

class ProductToProductCardViewStateMapper @Inject constructor(
) {
    fun mapProductToProductCardView(product: com.sagarika.data.entities.Product): ProductCardViewState {
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
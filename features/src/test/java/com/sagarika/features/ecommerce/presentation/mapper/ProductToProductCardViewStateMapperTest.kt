package com.sagarika.features.ecommerce.presentation.mapper

import com.sagarika.domain.entities.Product
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductCardViewState
import org.junit.Assert.*

import org.junit.Test

class ProductToProductCardViewStateMapperTest {
    private lateinit var productCardViewStateMapper: ProductToProductCardViewStateMapper

    companion object {
        val product = Product(
            title = "Item",
            description = "Desc",
            price = 250.0,
            imageUrl = "",
            productId = "1"
        )

        val productCardViewState = ProductCardViewState(
            id = "1",
            title = "Item",
            description = "Desc",
            price = "INR 250.0",
            imageUrl = "",
            isFavorite = false
        )
    }

    @Test
    fun `when mapper function is called then it maps Product To ProductCardView correctly`() {
        productCardViewStateMapper = ProductToProductCardViewStateMapper()
        val productCardView = productCardViewStateMapper.mapProductToProductCardView(product)

        assert(productCardView == productCardViewState)
    }
}
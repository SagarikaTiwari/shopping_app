package com.sagarika.data.mapper

import com.sagarika.domain.entities.ProductDetails
import com.sagarika.data.entities.ProductDetailsEntity
import javax.inject.Inject

class ProductDetailsEntityDataMapper @Inject constructor() {
    fun mapProductDetailsEntityToProductDetails(productDetailsEntity: ProductDetailsEntity): ProductDetails {
        with(productDetailsEntity) {
            return ProductDetails(
                title,
                description,
                full_description,
                "INR  ${price}",
                imageUrl,
                pros,
                cons
            )
        }
    }
}

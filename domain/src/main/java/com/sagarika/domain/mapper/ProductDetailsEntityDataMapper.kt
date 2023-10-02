package com.sagarika.domain.mapper

 import com.sagarika.data.entities.ProductDetailsEntity
import com.sagarika.domain.entities.ProductDetails
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

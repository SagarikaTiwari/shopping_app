package com.sagarika.domain.mapper

import com.sagarika.data.entities.ProductEntity
import com.sagarika.domain.entities.Product
import javax.inject.Inject

class ProductEntityDataMapper @Inject constructor() {

    fun mapProdcutEntityToProduct(
        productEntity: ProductEntity
    ): Product {
        with(productEntity) {
            return Product(
                title,
                description,
                price,
                imageUrl,
                id
            )
        }
    }
}
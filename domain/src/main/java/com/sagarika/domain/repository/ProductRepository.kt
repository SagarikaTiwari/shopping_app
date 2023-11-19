package com.sagarika.domain.repository

import com.sagarika.common.util.Resource
import com.sagarika.domain.entities.Product
import com.sagarika.domain.entities.ProductDetails

interface ProductRepository {
    suspend fun getProductList(): Resource<List<Product>>
    suspend fun getProductDetails(productId: String): Resource<ProductDetails>
}
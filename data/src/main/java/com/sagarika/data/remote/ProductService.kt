package com.sagarika.data.remote

import com.sagarika.data.entities.ProductDetailsEntity
import com.sagarika.data.entities.ProductEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    suspend fun getProductList(): List<ProductEntity>
    @GET("productDetails")
    suspend fun getProductDetails(@Query("productId") productId: String): ProductDetailsEntity
}
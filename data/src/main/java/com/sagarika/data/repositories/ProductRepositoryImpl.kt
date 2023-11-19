package com.sagarika.data.repositories

import com.sagarika.domain.entities.Product
import com.sagarika.data.mapper.ProductDetailsEntityDataMapper
import com.sagarika.data.mapper.ProductEntityDataMapper
import com.sagarika.data.remote.ProductService
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val service: ProductService,
    private val productEntityToProductDataMapper: ProductEntityDataMapper,
    private val productDetailsEntityDataMapper: ProductDetailsEntityDataMapper
) : ProductRepository {
    override suspend fun getProductList(): com.sagarika.common.util.Resource<List<Product>> {
        return try {
            com.sagarika.common.util.Resource.Success(service.getProductList().map {
                productEntityToProductDataMapper.mapProdcutEntityToProduct(it)
            })
        } catch (e: Exception) {
            com.sagarika.common.util.Resource.Error(e.localizedMessage)
        }
    }
    override suspend fun getProductDetails(productId: String): com.sagarika.common.util.Resource<ProductDetails> {
        return try {
            com.sagarika.common.util.Resource.Success(
                productDetailsEntityDataMapper.mapProductDetailsEntityToProductDetails(
                    service.getProductDetails(productId)
                )
            )
        } catch (e: Exception) {
            com.sagarika.common.util.Resource.Error(e.localizedMessage)
        }
    }
}
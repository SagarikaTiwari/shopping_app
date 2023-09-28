package com.sagarika.data.repositories

import com.sagarika.domain.entities.Product
import com.sagarika.domain.mapper.ProductDetailsEntityDataMapper
import com.sagarika.domain.mapper.ProductEntityDataMapper
import com.sagarika.domain.repositories.ProductRepository
import com.sagarika.data.remote.ProductService
import com.sagarika.data.remote.Resource
import com.sagarika.domain.entities.ProductDetails
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val service: ProductService,
    private val productEntityToProductDataMapper: ProductEntityDataMapper,
    private val productDetailsEntityDataMapper: ProductDetailsEntityDataMapper
) :
    ProductRepository {

    override suspend fun getProductList(): Resource<List<Product>> {
        return try {
            Resource.Success(service.getProductList().map {
                productEntityToProductDataMapper.mapProdcutEntityToProduct(it)
            })
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }


    override suspend fun getProductDetails(productId: String): Resource<ProductDetails> {
        return try {
            Resource.Success(
                productDetailsEntityDataMapper.mapProductDetailsEntityToProductDetails(
                    service.getProductDetails(productId)
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}
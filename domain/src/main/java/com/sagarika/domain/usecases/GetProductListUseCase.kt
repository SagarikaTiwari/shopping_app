package com.sagarika.domain.usecases

import com.sagarika.data.remote.Resource
import com.sagarika.domain.entities.Product
import com.sagarika.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return productRepository.getProductList()
    }
}
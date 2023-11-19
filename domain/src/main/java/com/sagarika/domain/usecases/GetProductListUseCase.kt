package com.sagarika.domain.usecases

import com.sagarika.common.util.Resource
import com.sagarika.domain.entities.Product
import com.sagarika.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return productRepository.getProductList()
    }
}
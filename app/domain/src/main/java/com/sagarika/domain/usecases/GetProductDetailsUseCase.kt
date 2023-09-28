package com.sagarika.domain.usecases

import com.sagarika.data.remote.Resource
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(productId: String): Resource<ProductDetails> {
        return productRepository.getProductDetails(productId)
    }
}
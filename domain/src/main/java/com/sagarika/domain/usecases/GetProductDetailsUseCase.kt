package com.sagarika.domain.usecases

import com.sagarika.common.util.Resource
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository : ProductRepository

) { suspend operator fun invoke(productId: String): Resource<ProductDetails> {
        return productRepository.getProductDetails(productId)
    }
}
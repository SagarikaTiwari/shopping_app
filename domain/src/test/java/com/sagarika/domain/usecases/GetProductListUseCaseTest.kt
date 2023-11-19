package com.sagarika.domain.usecases

import com.sagarika.common.util.Resource
import com.sagarika.domain.entities.Product
import com.sagarika.domain.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetProductListUseCaseTest {
    companion object {
        private val productRepository = mockk<ProductRepository>()
        private val listOfProducts = (0..2).map {
            Product(
                "title",
                "description",
                100.0,
                "",
                "$it"
            )
        }
        private lateinit var useCase: GetProductListUseCase
    }

    @Before
    fun setUp() {
        useCase = GetProductListUseCase(productRepository)
    }

    @Test
    fun `When getProductList is called then List gets populated correctly`() = runTest {
        coEvery {
            productRepository.getProductList()
        } returns Resource.Success(listOfProducts)
        useCase()
        coVerify { productRepository.getProductList() }
    }
}
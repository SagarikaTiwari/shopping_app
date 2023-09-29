package com.sagarika.domain.usecases

import com.sagarika.data.remote.Resource
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.repositories.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetProductDetailsUseCaseTest {

    companion object {
        private val productRepository = mockk<ProductRepository>()
        private val productDetails =
            ProductDetails(
                "title",
                "description",
                "full description",
                "100.0",
                "",
                listOf("pros"),
                listOf("cons")
            )
        private lateinit var useCase: GetProductDetailsUseCase
    }
    @Before
    fun setUp() {
        useCase = GetProductDetailsUseCase(productRepository)
    }

    @Test
    fun `When getProductDetails is called then Details gets populated correctly`() = runTest {
        coEvery {
            productRepository.getProductDetails("any Id")
        } returns Resource.Success(productDetails)
        useCase("any Id")
        coVerify { productRepository.getProductDetails("any Id")}
    }

}
package com.sagarika.features.ecommerce.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sagarika.data.remote.Resource
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.usecases.GetProductDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest {

    companion object {

        private val loadProductDetailsUseCase = mockk<GetProductDetailsUseCase>()
        private lateinit var viewModel: ProductDetailsViewModel
        private var isLoading = false

        val productDetails =
            ProductDetails(
                "title",
                "description",
                "full description",
                "100.0",
                "",
                listOf("pros"),
                listOf("cons")
            )
    }


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        viewModel = ProductDetailsViewModel(loadProductDetailsUseCase, dispatcher)
    }

    @Test
    fun `When isLoading is true then the view state shows loading`() {
        coEvery { isLoading } returns true
        val viewStates = mutableListOf<ProductDetailsViewState>()

        viewModel.loadProduct("1")
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is ProductDetailsViewState.Loading)
    }

    @Test
    fun `When get product details return success then view state is updated correctly`() {
        coEvery { loadProductDetailsUseCase("1") } returns Resource.Success(
            Companion.productDetails
        )
        val viewStates = mutableListOf<ProductDetailsViewState>()

        viewModel.loadProduct("1")
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is ProductDetailsViewState.Content)
    }

    @Test
    fun `When get product details return error then view state is updated with Error`() {
        coEvery { loadProductDetailsUseCase("1") } returns Resource.Error(
            "An error occurred !"
        )
        val viewStates = mutableListOf<ProductDetailsViewState>()

        viewModel.loadProduct("1")
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is ProductDetailsViewState.Error)
    }



}
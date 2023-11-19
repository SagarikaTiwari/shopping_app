package com.sagarika.features.ecommerce.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sagarika.common.util.Resource
import com.sagarika.domain.entities.Product
import com.sagarika.domain.usecases.GetProductListUseCase
import com.sagarika.features.ecommerce.presentation.mapper.ProductToProductCardViewStateMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    companion object {
        @get:Rule
        var rule: TestRule = InstantTaskExecutorRule()
        private val dispatcher = StandardTestDispatcher()
        private lateinit var viewModel: ProductListViewModel
        private val loadProductListUseCase = mockk<GetProductListUseCase>()
        private val productToProductCardViewStateMapper =
            mockk<ProductToProductCardViewStateMapper>()
        private val listOfProduct = (0..2).map {
            Product("title", "description", 6.0, "", "$it")
        }
        private lateinit var result: Resource.Success<List<Product>>
        private var isLoading = false
    }

    @Before
    fun setUp() {
        viewModel = ProductListViewModel(
            loadProductListUseCase,
            dispatcher,
            productToProductCardViewStateMapper
        )
    }

    @Test
    fun `When isLoading is true  then show Loading state`() = runTest {
        coEvery { isLoading } returns true

        val viewStates = mutableListOf<ProductListViewState>()
        viewModel.viewState.collect() {
            viewStates.add(it)
        }

        viewModel.loadProductList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewStates[0] is ProductListViewState.Loading)
    }

    @Test
    fun `When productList loads returns corrects then shows Success`() = runTest {
        coEvery {
            loadProductListUseCase()
        } returns Resource.Success(listOfProduct)

        val viewStates = mutableListOf<ProductListViewState>()

        viewModel.viewState.collect() {
            viewStates.add(it)
        }

        viewModel.loadProductList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewStates[0] is ProductListViewState.Content)
    }

    @Test
    fun `When loadProductList resturns error then ViewState updates to Error`() = runTest {
        coEvery {
            loadProductListUseCase()
        } returns Resource.Error("An error occurred !")

        val viewStates = mutableListOf<ProductListViewState>()
        viewModel.viewState.collect() {
            viewStates.add(it)
        }

        viewModel.loadProductList()
        dispatcher.scheduler.advanceUntilIdle()

        assert(viewStates[0] is ProductListViewState.Error)
    }

}
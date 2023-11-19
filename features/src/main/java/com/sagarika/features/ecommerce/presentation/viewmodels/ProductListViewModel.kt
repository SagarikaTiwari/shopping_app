package com.sagarika.features.ecommerce.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagarika.common.util.Resource
import com.sagarika.domain.usecases.GetProductListUseCase
import com.sagarika.features.ecommerce.presentation.mapper.ProductToProductCardViewStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(

    private val getProductListUseCase: GetProductListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private var productToProductCardViewStateMapper: ProductToProductCardViewStateMapper,

    ) : ViewModel() {

    private val _viewState = MutableStateFlow<ProductListViewState>(ProductListViewState.Loading)
    val viewState: StateFlow<ProductListViewState> = _viewState
    private var isLoading = false

    fun loadProductList() {
        viewModelScope.launch(dispatcher) {
            isLoading = true
            when (val productList = getProductListUseCase()) {
                is Resource.Error -> {
                    _viewState.value = ProductListViewState.Error
                }
                is Resource.Success -> {
                    _viewState.value = productList.data.let {
                        ProductListViewState.Content(it.map {
                            productToProductCardViewStateMapper.mapProductToProductCardView(it)
                        })
                    }
                }
            }
        }
    }
}

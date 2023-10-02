package com.sagarika.features.ecommerce.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagarika.domain.usecases.GetProductDetailsUseCase
import com.sagarika.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<ProductDetailsViewState>(ProductDetailsViewState.Loading)
    val viewState: StateFlow<ProductDetailsViewState> = _viewState
    var isLoading = false



    fun loadProduct(productId: String) {
        viewModelScope.launch(dispatcher) {
            isLoading = true
            when (val productDetails = getProductDetailsUseCase(productId)) {
                is Resource.Error -> {
                    _viewState.value = ProductDetailsViewState.Error
                    isLoading = false
                }

                is Resource.Success -> {
                    _viewState.value =
                        ProductDetailsViewState.Content(
                            productDetails.data!!
                        )
                    isLoading = false
                }
            }
        }
    }
}
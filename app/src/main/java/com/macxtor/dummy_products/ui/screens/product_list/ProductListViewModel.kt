package com.macxtor.dummy_products.ui.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.domain.model.Product
import com.macxtor.dummy_products.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _productListState: MutableStateFlow<ProductListState> =
        MutableStateFlow(ProductListState.Loading)

    val productListState = _productListState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = ProductListState.Loading
    )

    fun fetchAllProducts() {
        viewModelScope.launch {
            val response = getAllProductsUseCase.execute()
            handleGetAllProductResponse(response)
        }
    }

    private fun handleGetAllProductResponse(response: Result<List<Product>>) {
        when (response) {
            is Result.Success -> setProductListState(ProductListState.Success(response.data))
            is Result.Error -> setProductListState(ProductListState.Error(response.exception))
        }
    }

    private fun setProductListState(state: ProductListState) {
        _productListState.update {
            state
        }
    }
}
package com.macxtor.dummy_products.ui.screens.product_list

import com.macxtor.dummy_products.domain.model.Product

sealed class ProductListState {
    object Loading : ProductListState()
    data class Success(val listOfProducts: List<Product>) : ProductListState()
    data class Error(val exception: Exception) : ProductListState()
}
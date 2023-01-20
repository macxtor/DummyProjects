package com.macxtor.dummy_products.domain.repository

import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.domain.model.Product

interface ProductsRepository {
    suspend fun getAllProducts(): Result<List<Product>>
}
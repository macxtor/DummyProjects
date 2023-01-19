package com.macxtor.dummy_products.data.repository

import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.data.api.ProductsApiService
import com.macxtor.dummy_products.data.toDomain
import com.macxtor.dummy_products.domain.model.Product
import com.macxtor.dummy_products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    val productApiService: ProductsApiService
) : ProductsRepository {

    override suspend fun getAllProducts(): Result<List<Product>> {
        return try {
            Result.Success(productApiService.getAllProducts().toDomain())
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
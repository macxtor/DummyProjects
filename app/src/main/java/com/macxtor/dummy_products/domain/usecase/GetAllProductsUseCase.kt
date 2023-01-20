package com.macxtor.dummy_products.domain.usecase

import com.macxtor.dummy_products.domain.repository.ProductsRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend fun execute() = productsRepository.getAllProducts()
}
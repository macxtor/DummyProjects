package com.macxtor.dummy_products.data.api

import com.macxtor.dummy_products.data.dto.ProductListDto
import retrofit2.http.GET
interface ProductsApiService {
    @GET("products")
    suspend fun getAllProducts(): ProductListDto
}
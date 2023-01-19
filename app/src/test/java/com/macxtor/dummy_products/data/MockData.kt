package com.macxtor.dummy_products.data

import com.macxtor.dummy_products.data.dto.ProductDto
import com.macxtor.dummy_products.data.dto.ProductListDto
import com.macxtor.dummy_products.domain.model.Product

val mockProductDto = ProductDto(
    id = 1,
    title = "title",
    description = "description",
    price = 10,
    discountPercentage = 10.0,
    rating = 5.0,
    stock = 34,
    brand = "brand",
    category = "category",
    thumbnail = "thumbnail",
    images = listOf()
)

val mockProductListDto = ProductListDto(
    total = 1,
    skip = 1,
    products = listOf(mockProductDto),
    limit = 1
)

val mockProduct = Product(
    id = 1,
    title = "title",
    description = "description",
    price = 10,
    discountPercentage = 10.0,
    rating = 5.0,
    stock = 34,
    brand = "brand",
    category = "category",
    thumbnail = "thumbnail",
    images = listOf()
)

val mockProductList = listOf(mockProduct)
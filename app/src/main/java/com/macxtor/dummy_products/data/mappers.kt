package com.macxtor.dummy_products.data

import com.macxtor.dummy_products.data.dto.ProductDto
import com.macxtor.dummy_products.data.dto.ProductListDto
import com.macxtor.dummy_products.domain.model.Product


fun ProductListDto.toDomain()= this.products.map { it.toDomain() }

fun ProductDto.toDomain() = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand,
    category = category,
    thumbnail = thumbnail,
    images = images
)
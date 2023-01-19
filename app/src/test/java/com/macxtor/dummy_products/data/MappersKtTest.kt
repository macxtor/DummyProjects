package com.macxtor.dummy_products.data

import org.junit.Assert.assertEquals
import org.junit.Test

internal class MappersKtTest {

    @Test
    fun `Given productListDto object when mapped to domain returns valid list of products `() {
        val given = mockProductListDto
        val expected = mockProductList

        val result = given.toDomain()

        assertEquals(result, expected)
    }

    @Test
    fun `Given productDto object when mapped to domain returns valid product entity `() {
        val given = mockProductDto
        val expected = mockProduct

        val result = given.toDomain()

        assertEquals(result, expected)
    }
}
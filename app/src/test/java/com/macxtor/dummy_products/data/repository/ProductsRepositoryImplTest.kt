package com.macxtor.dummy_products.data.repository

import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.data.api.ProductsApiService
import com.macxtor.dummy_products.data.mockProductListDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductsRepositoryImplTest {

    @MockK
    lateinit var productsApiService: ProductsApiService

    lateinit var productsRepositoryImpl: ProductsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productsRepositoryImpl = ProductsRepositoryImpl(productsApiService)
    }


    @Test
    fun `when getAllProducts is called and api service request is successful then repository send valid success data`() =
        runTest {
            coEvery { productsApiService.getAllProducts() } returns mockProductListDto

            val result = productsRepositoryImpl.getAllProducts()

            assertThat(
                result,
                instanceOf(Result.Success::class.java)
            )
        }

    @Test
    fun `when getAllProducts is called and api service request throws exception then repository send error`() =
        runTest {
            coEvery { productsApiService.getAllProducts() } throws Exception()

            val result = productsRepositoryImpl.getAllProducts()

            assertThat(
                result,
                instanceOf(Result.Error::class.java)
            )
        }
}
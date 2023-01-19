package com.macxtor.dummy_products.ui.screens.product_list

import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.data.utils.CoroutineTestRule
import com.macxtor.dummy_products.domain.model.Product
import com.macxtor.dummy_products.domain.usecase.GetAllProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ProductListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @MockK
    private lateinit var getAllProductsUseCase: GetAllProductsUseCase

    private lateinit var productListViewModel: ProductListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productListViewModel = ProductListViewModel(getAllProductsUseCase)
    }

    @Test
    fun `should retrieve list of all products when network request is successful`() =
        runTest {
            val expectedProduct = Product(
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
            val expectedListOfProducts = listOf(expectedProduct)
            coEvery { getAllProductsUseCase.execute() } returns Result.Success(
                data = expectedListOfProducts
            )

            productListViewModel.fetchAllProducts()
            val result = productListViewModel.productListState

            assertEquals(ProductListState.Loading, result.value)
            advanceUntilIdle()
            assertEquals(
                expectedProduct,
                (productListViewModel.productListState.value as ProductListState.Success).listOfProducts.first()
            )
        }

    @Test
    fun `should show exception when network request is unsuccessful`() =
        runTest {
            val exception = Exception()
            Result.Error(exception = exception)
            coEvery { getAllProductsUseCase.execute() } returns Result.Error(
                exception = exception
            )

            productListViewModel.fetchAllProducts()
            val result = productListViewModel.productListState

            assertEquals(ProductListState.Loading, result.value)
            advanceUntilIdle()
            assertEquals(
                exception,
                (productListViewModel.productListState.value as ProductListState.Error).exception
            )
        }
}
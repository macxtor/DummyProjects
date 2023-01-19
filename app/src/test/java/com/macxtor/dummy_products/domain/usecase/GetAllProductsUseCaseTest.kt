package com.macxtor.dummy_products.domain.usecase

import com.macxtor.dummy_products.core.Result
import com.macxtor.dummy_products.domain.repository.ProductsRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.lang.Exception

internal class GetAllProductsUseCaseTest {

    @MockK
    val repository: ProductsRepository = mockk()

    private lateinit var getAllProductsUseCase: GetAllProductsUseCase

    @Before
    fun setup() {
        getAllProductsUseCase = GetAllProductsUseCase(repository)
    }

    @Test
    fun `When network request is successful then getAllProducts returns success`() =
        runTest {
            coEvery { repository.getAllProducts() } returns Result.Success(listOf())

            val result = getAllProductsUseCase.execute()

            assertThat(result, instanceOf(Result.Success::class.java))
        }

    @Test
    fun `Given valid params when network request fails then getCreditPartnerInformation return failure`() =
        runTest {
            coEvery { repository.getAllProducts() } returns Result.Error(Exception())

            val result = getAllProductsUseCase.execute()

            assertThat(result, instanceOf(Result.Error::class.java))
        }
}

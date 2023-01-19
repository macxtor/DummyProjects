package com.macxtor.dummy_products.data.di

import com.macxtor.dummy_products.data.api.ProductsApiService
import com.macxtor.dummy_products.data.repository.ProductsRepositoryImpl
import com.macxtor.dummy_products.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideProductsRetrofitApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideProductsApiService(retrofit: Retrofit): ProductsApiService =
        retrofit.create(ProductsApiService::class.java)

    @Provides
    fun provideProductsRepository(
        productsApiService: ProductsApiService
    ): ProductsRepository {
        return ProductsRepositoryImpl(productsApiService)
    }
}
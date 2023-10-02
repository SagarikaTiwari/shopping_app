package com.example.e_commerceappwithjetpackcompose.di

import com.sagarika.data.remote.ApiClient
import com.sagarika.data.remote.ProductService
import com.sagarika.data.repositories.ProductRepositoryImpl
import com.sagarika.domain.mapper.ProductDetailsEntityDataMapper
import com.sagarika.domain.mapper.ProductEntityDataMapper
import com.sagarika.domain.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesProductService(): ProductService = ApiClient.getService()

    @Provides
    fun providedsProductRepositoryAPI(
        service: ProductService,
        productEntityToProductDataMapper: ProductEntityDataMapper,
        productDetailsEntityDataMapper : ProductDetailsEntityDataMapper
    ): ProductRepositoryImpl = ProductRepositoryImpl(service, productEntityToProductDataMapper,productDetailsEntityDataMapper)

    @Provides
    fun providesProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository = productRepositoryImpl



    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
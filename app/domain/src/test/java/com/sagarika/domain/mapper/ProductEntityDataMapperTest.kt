package com.sagarika.domain.mapper

import com.sagarika.data.entities.ProductEntity
import com.sagarika.domain.entities.Product
import com.sagarika.domain.mapper.ProductEntityDataMapper
import org.junit.Test

internal class ProductEntityDataMapperTest {

    companion object {
        private lateinit var productDetailsEntityDataMapper: ProductEntityDataMapper
        private val productEntity = ProductEntity(
            "1",
            "title",
            "desc",
            100.0,
            ""
        )
        private val product = Product(
            "title",
            "desc",
            100.0,
            "",
            "1"
        )
    }

    @Test
    fun `when mapper function is called then it maps ProductEntity to Product correctly`() {
        productDetailsEntityDataMapper = ProductEntityDataMapper()
        val productEntity = productDetailsEntityDataMapper.mapProdcutEntityToProduct(productEntity)

        val productEntityExpected = product

        assert(productEntity == productEntityExpected)

    }

}
package com.sagarika.data.mapper

import com.sagarika.data.entities.ProductDetailsEntity
import com.sagarika.domain.entities.ProductDetails
import org.junit.Test

internal class ProductDetailsEntityDataMapperTest {
    companion object{
        private val productDetailsEntity = ProductDetailsEntity(
            "title",
            "description",
            "full description",
            100.0,
            "",
            listOf("pros"),
            listOf("cons")
        )
        private lateinit var productDetailsEntityDataMapper: com.sagarika.data.mapper.ProductDetailsEntityDataMapper
        private val productDetail = ProductDetails(
            title = "title",
            description = "description",
            fullDescription = "full description",
            price = "INR  100.0",
            imageUrl = "",
            pros = listOf("pros"),
            cons = listOf("cons")
        )
    }
    @Test
    fun `when mapper function is called then it maps productDetailsEntity to productDetails`() {
        productDetailsEntityDataMapper = com.sagarika.data.mapper.ProductDetailsEntityDataMapper()
        val productDetails = productDetailsEntityDataMapper.mapProductDetailsEntityToProductDetails(
            productDetailsEntity
        )
        val productDetailsExcpected = productDetail
        assert(
            productDetails == productDetailsExcpected
        )
    }
}
package com.example.e_commerceappwithjetpackcompose.repositories

import com.sagarika.data.entities.ProductDetailsEntity
import com.sagarika.data.entities.ProductEntity
import com.sagarika.data.remote.ProductService
import com.sagarika.data.remote.Resource
import com.sagarika.data.repositories.ProductRepositoryImpl
import com.sagarika.domain.entities.Product
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.domain.mapper.ProductDetailsEntityDataMapper
import com.sagarika.domain.mapper.ProductEntityDataMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductRepositoryImplTest {

    companion object {
        private lateinit var repository: ProductRepositoryImpl
        private val service = mockk<ProductService>()
        private val productEntityToProductDataMapper = mockk<ProductEntityDataMapper>()
        private val productDetailsEntityDataMapper = mockk<ProductDetailsEntityDataMapper>()
        val listOfProductEntity = (0..2).map {
            ProductEntity(
                "id",
                "title",
                "description $it",
                6.0,
                ""
            )
        }
        val listOfProducts = (0..2).map {
            Product(
                "title",
                "description $it",
                6.0,
                "",
                "id"
            )
        }

        val productDetailEntity = ProductDetailsEntity(
            "title",
            "desc",
            "full_desc",
            10.0,
            "",
            listOf("pros"),
            listOf("cons")
        )
        val productDetail = ProductDetails(
            "title",
            "desc",
            "full_desc",
            "10.0",
            "",
            listOf("pros"),
            listOf("cons")
        )
    }

    @Before
    fun setup() {
        repository = ProductRepositoryImpl(
            service,
            productEntityToProductDataMapper,
            productDetailsEntityDataMapper
        )
    }


    @Test
    fun `when get product List is  called then it returns entity into Business Objects`() =
        runTest {

            coEvery {
                service.getProductList()
            } returns listOfProductEntity

            coEvery {
                productEntityToProductDataMapper.mapProdcutEntityToProduct(
                    listOfProductEntity[0]
                )
            } returns listOfProducts[0]
            coEvery {
                productEntityToProductDataMapper.mapProdcutEntityToProduct(
                    listOfProductEntity[1]
                )
            } returns listOfProducts.get(1)
            coEvery {
                productEntityToProductDataMapper.mapProdcutEntityToProduct(
                    listOfProductEntity[2]
                )
            } returns listOfProducts.get(2)

            val products = (service.getProductList())

            assert(products.size == 3)
            if (products != null) {
                assert(products.get(1).description == "description 1")
            }
        }


    @Test
    fun `when get Product Detail is called then it returns entity into business object`() =
        runTest {
            coEvery {
                service.getProductDetails(any())
            } returns productDetailEntity

            coEvery {
                productDetailsEntityDataMapper.mapProductDetailsEntityToProductDetails(
                    productDetailEntity
                )
            } returns productDetail

            assert(service.getProductDetails("1").title == "title")
            assert(service.getProductDetails("13").description == "desc")
            assert(service.getProductDetails("23").price == 10.0)

        }
}
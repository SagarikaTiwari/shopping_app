package com.sagarika.features.ecommerce.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sagarika.domain.entities.ProductDetails
import com.sagarika.features.ecommerce.presentation.theme.Roboto
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductDetailsViewState
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductDetailsViewModel
import com.sagarika.features.ecommerce.presentation.widget.ErrorViewInABox
import com.sagarika.features.ecommerce.presentation.widget.LoadingIndicator
import com.sagarika.features.ecommerce.presentation.widget.MyTextView
import java.util.Locale

@Composable
fun ProductDetailScreen(
    productId: String,
    navController: NavController,
    productDetailsViewModel: ProductDetailsViewModel
) {
    productDetailsViewModel.loadProduct(productId = productId)
    val viewState = productDetailsViewModel.viewState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            if (viewState.equals(ProductDetailsViewState.Loading)) {
                LoadingIndicator()
            } else if (viewState.equals(ProductDetailsViewState.Error)) {
                ErrorViewInABox()
            } else {
                val product = (viewState as ProductDetailsViewState.Content).product
                ProductTitleAndImage(product = product)
                Spacer(modifier = Modifier.height(30.dp))
                ProductDescription(product = product)
                Spacer(modifier = Modifier.height(30.dp))
             }

        }
    }
}

@Composable
fun ProductTitleAndImage(product: ProductDetails) {

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.Transparent
                    )
                )
            )
            .fillMaxWidth(1f)
    ) {
        Column() {


            MyTextView(
                text = "${product.title.capitalize(Locale.ROOT)}",
                fontFamily = Roboto,
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                color = Color(0xff2e3156), modifier = Modifier
                    .offset(16.dp, 16.dp)
                    .fillMaxWidth(0.8f)
            )
            val painter = rememberAsyncImagePainter(
                product.imageUrl
            )
            Icon(
                painter = painter,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .offset(16.dp, 20.dp)
                    .padding(5.dp)

            )

        }
    }
}

@Composable
fun ProductDescription(product: ProductDetails) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color.LightGray, shape = RectangleShape)
    )
    MyTextView(
        text = product.fullDescription,
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontFamily = Roboto,
        fontSize = 10.sp,
        modifier = Modifier.fillMaxSize()
    )

}




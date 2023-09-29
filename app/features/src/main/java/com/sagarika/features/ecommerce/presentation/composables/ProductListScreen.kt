package com.sagarika.features.ecommerce.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sagarika.features.R
import com.sagarika.features.ecommerce.presentation.theme.RobotoCondensed
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductCardViewState
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductListViewModel
import com.sagarika.features.ecommerce.presentation.viewmodels.ProductListViewState
import com.sagarika.features.ecommerce.presentation.widget.ErrorViewInABox
import com.sagarika.features.ecommerce.presentation.widget.LoadingIndicator
import com.sagarika.features.ecommerce.presentation.widget.MyTextView

@Composable
fun ProductListScreen(
    navController: NavController,
    productListViewModel: ProductListViewModel
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        productListViewModel.loadProductList()
        ScaffoldWithTopBar(navController, productListViewModel)
    }
}

@Composable
fun ProductList(
    navController: NavController,
    productListViewModel: ProductListViewModel,
    paddingValues: PaddingValues
) {
    val productListState: ProductListViewState by productListViewModel.viewState.collectAsState()

    when (productListState) {
        is ProductListViewState.Loading ->
            LoadingIndicator()


        is ProductListViewState.Error -> {
            ErrorViewInABox()
        }

        is ProductListViewState.Content -> {
            val productList = (productListState as ProductListViewState.Content).productList
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                items(productList.size) {
                    Card(
                        border = BorderStroke(2.dp, color = MaterialTheme.colors.background),
                        elevation = 15.dp,
                    ) {
                        ProductListRow(
                            rowIndex = it,
                            productList = productList,
                            navController = navController,
                            productListViewModel = productListViewModel
                        )
                    }
                }
            }
        }

        else -> {}
    }


}

@Composable
fun ScaffoldWithTopBar(navController: NavController, productListViewModel: ProductListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }, content = {

            ProductList(navController = navController, productListViewModel, it)

        })
}

@Composable
fun ProductListRow(
    rowIndex: Int,
    productList: List<ProductCardViewState>,
    navController: NavController,
    productListViewModel: ProductListViewModel
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    navController.navigate("product_detail_screen/${productList.get(rowIndex).id}")
                },
        ) {
            val painter = rememberAsyncImagePainter(
                productList.get(index = rowIndex).imageUrl
            )
            Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.itemhpoto),
                modifier = Modifier
                    .size(120.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            TitleAndDescription(productList.get(rowIndex))
        }

    }
}

@Composable
fun TitleAndDescription(productCard: ProductCardViewState) {

    Column(
        modifier = Modifier.padding(10.dp),
    ) {

        MyTextView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            text = productCard.title,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = RobotoCondensed,
            textAlign = TextAlign.Start,
        )


        MyTextView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            text = productCard.description,
            color = Color.Black,
            fontSize = 10.sp,
            fontFamily = RobotoCondensed,
            textAlign = TextAlign.Start
        )

        MyTextView(
            modifier = Modifier
                .fillMaxWidth(),
            text = productCard.price,
            color = Color.Black,
            fontSize = 10.sp,
            fontFamily = RobotoCondensed,
            textAlign = TextAlign.End
        )


    }

}
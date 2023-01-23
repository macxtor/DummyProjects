package com.macxtor.dummy_products.ui.screens.product_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.macxtor.dummy_products.R
import com.macxtor.dummy_products.domain.model.Product
import com.macxtor.dummy_products.ui.theme.spacing_16
import com.macxtor.dummy_products.ui.theme.spacing_8

@Composable
fun ProductsListScreen(viewModel: ProductListViewModel = hiltViewModel()) {
    val state = viewModel.productListState.collectAsState()
    viewModel.fetchAllProducts()
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Product List") }) },
        content = { paddingValues ->
            ProductListContent(
                state = state.value,
                paddingValues = paddingValues
            )
        })

}

@Composable
fun ProductListContent(paddingValues: PaddingValues, state: ProductListState) {
    when (state) {
        is ProductListState.Success -> ProductListSuccess(
            listOfProducts = state.listOfProducts
        )
        is ProductListState.Loading -> LoadingView()
        is ProductListState.Error -> ProductListError(exception = state.exception.message)
    }
}

@Composable
fun ProductListSuccess(listOfProducts: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listOfProducts) { product ->
            ProductCard(product = product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.padding(spacing_16), shape = RoundedCornerShape(spacing_8),
        elevation = spacing_8,
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(product.thumbnail),
                contentDescription = stringResource(id = R.string.product_thumbnail_image),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(150.dp)
                    .width(200.dp)
                    .background(color = Color.LightGray)
            )
            Text(
                product.category,
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(top = spacing_16, start = spacing_16, end = spacing_16)
            )
            Text(
                product.title,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = spacing_16, end = spacing_16)
            )
            Text(
                product.brand,
                style = MaterialTheme.typography.body2.copy(color = Color.Red),
                modifier = Modifier.padding(
                    start = spacing_16,
                    end = spacing_16,
                    bottom = spacing_16
                )
            )

        }

    }
}

@Composable
fun ProductListError(exception: String?) {
    exception?.let {
        Column(
            modifier = Modifier.padding(spacing_16),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = it)
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.padding(spacing_16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
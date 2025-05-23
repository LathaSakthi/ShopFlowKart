package com.lathavel.shopflowkart.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lathavel.shopflowkart.R
import com.lathavel.shopflowkart.data.model.CategoryItem
import com.lathavel.shopflowkart.data.model.ProductItem
import com.lathavel.shopflowkart.ui.theme.Purple80
import com.lathavel.shopflowkart.ui.theme.ShopFlowKartTheme
import com.lathavel.shopflowkart.ui.theme.limeGreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel : MainViewmodel = viewModel()
            val productList by viewmodel.productList.collectAsState()
            val categoryList by viewmodel.categoryList.collectAsState()

            ShopFlowKartTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF2A2A2A)),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarColors(
                                containerColor = Color.DarkGray,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                                scrolledContainerColor = Color.DarkGray
                            ),
                            title = {
                                Text("Shop")
                            },
                            navigationIcon = {
                                IconButton(onClick = {  }) {
                                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            },
                            actions = {
                                IconButton(onClick = {  }) {
                                    Icon(Icons.Default.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = {  }) {
                                    Icon(Icons.Default.Favorite, contentDescription = "Notifications")
                                }
                                IconButton(onClick = {  }) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "More")
                                }
                            })}
                ) { innerPadding ->
                    
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .background(
                                    color = Color.DarkGray
                                ),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            item{
                                CardCarousel()
                                ShowCategories(categoryList)
                                ShowNewProducts()
                                productList.forEach {
                                    ShowProductItems(it)
                                }
                            }
                        }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardCarousel() {
    val cardItems = listOf("Card 1", "Card 2", "Card 3")
    val pagerState = rememberPagerState(pageCount = { cardItems.size })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { page ->
            ShowBanner()
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(cardItems.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (isSelected) 20.dp else 20.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.Red else Color.White)
                )
            }
        }
    }
}

@Composable
fun ShowBanner(){
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)){
            Text(text = "GET 20% OFF",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White)
            Text(text = "Get 20% Off",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White)

            Spacer(modifier = Modifier.height(10.dp))

            Row(){
                Text(text = "12-16 October",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                    modifier = Modifier
                        .background(color = limeGreen, RoundedCornerShape(18.dp))
                        .padding(5.dp))

                Spacer(modifier = Modifier.weight(1f))

                Image(painter = painterResource(R.drawable.categorysample),
                    modifier = Modifier
                        .width(70.dp)
                        .height(50.dp),
                    contentDescription = "image")


            }
        }
    }



}

@Composable
fun ShowHeading(headingName: String,
                heading2Name: String){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)){
        Text(headingName,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.weight(1f))
        Text(heading2Name,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}
@Composable
fun ShowCategories(categoryList: List<CategoryItem>) {

    ShowHeading("Categories", "See All")
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        items(categoryList){ category->

            Column(modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center){

                Image(
                    contentDescription = "",
                    painter = painterResource(id = category.imageUrl),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(color = Color.Black)
                )
                Text(category.categoryName,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }
}

@Composable
fun ShowNewProducts() {
    ShowHeading("New Products", "See All")
}

@Composable
fun ShowProductItems(productItem : ProductItem){

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

            Card(modifier = Modifier.padding(10.dp)) {

                Box(modifier = Modifier.fillMaxSize()){
                    Column {
                        Row{
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(imageVector = if (productItem.isFavorite) Icons.Rounded.Favorite
                                                         else  Icons.Rounded.FavoriteBorder,
                                    tint = if (productItem.isFavorite) Purple80 else Color.LightGray,
                                    contentDescription = "Favorite",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(color = Color.Black)
                                        .padding(10.dp))

                            }
                            Spacer(Modifier.weight(1f))

                            productItem.sellerCategory?.let{
                                ElevatedButton( colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                                    onClick = {}) {
                                    Text(text = it,
                                        color = limeGreen)
                                }
                            }

                        }

                        Column(modifier = Modifier.padding(10.dp).fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally){

                            Image(
                                contentDescription = "image",
                                painter = painterResource(id = productItem.imageUrl), modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                            )

                            ProductItemCardContent(productItem)

                        }

                    }



                }
            }

    }
}

@Composable
fun ProductItemCardContent(productItem: ProductItem){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        colors  = CardDefaults.cardColors(
            containerColor = Color.Black
        )){

    Box() {
        Column(modifier = Modifier.padding(10.dp)) {

            Row() {
                Text(
                    productItem.brandName,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif,
                    color = limeGreen
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "\u2022 ${if (productItem.isInStock) "In Stock" else "Out of Stock"}",
                    style = MaterialTheme.typography.labelMedium,
                    color =if (productItem.isInStock) limeGreen else Color(0xFFFCA300)
                )

            }
            Text( productItem.productName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                "${productItem.categoryName} \u2022 ${productItem.categoryName2}",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Row() {
                Text(
                    "Rs. ${productItem.actualValue}",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif,
                    color = Purple80
                )
                Text(
                    "Rs. ${productItem.discountValue}",
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )

            }

            Row() {
                StarRatingBar(
                    rating = productItem.rating,
                    onRatingChanged = {})

                Text(
                    "${productItem.reviewCount} Reviews",
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Serif,
                    color = Purple80
                )
            }

        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(color = Color.Gray)
        ) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                tint = limeGreen,
                contentDescription = "shopping kart",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Black)
                    .padding(10.dp)
            )

        }
    }
    }
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    Row(
        Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = { onRatingChanged(i.toFloat()) }
                    )
                    .width(24.dp)
                    .height(24.dp)
            )
            if (i < maxStars) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
package com.lathavel.shopflowkart.ui.screen

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lathavel.shopflowkart.R
import com.lathavel.shopflowkart.data.model.CategoryItem
import com.lathavel.shopflowkart.data.model.ProductItem
import com.lathavel.shopflowkart.ui.theme.Purple80
import com.lathavel.shopflowkart.ui.theme.ShopFlowKartTheme
import com.lathavel.shopflowkart.ui.theme.limeGreen

class MainActivity : ComponentActivity() {

    private val viewmodel : MainViewmodel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val productList by viewmodel.productList.collectAsState()
            val categoryList by viewmodel.categoryList.collectAsState()

            ShopFlowKartTheme (dynamicColor = false){
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
              //      .background(color = Color(0xFFFCA9C4)),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                scrolledContainerColor = MaterialTheme.colorScheme.surfaceVariant, // Or surfaceColorAtElevation(3.dp)
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                                IconButton(onClick = {
                                    Toast.makeText(this@MainActivity, "Search is clicked", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(Icons.Default.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = {
                                    Toast.makeText(this@MainActivity, "Favorite is clicked", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                                }
                                IconButton(onClick = {
                                    Toast.makeText(this@MainActivity, "Kart is clicked", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "More")
                                }
                            })}
                ) { innerPadding ->
                    ShopFlowKartScreen(viewmodel,productList, categoryList, innerPadding)
                }
            }
        }
    }
}

@Composable
fun ShopFlowKartScreen(
    viewmodel: MainViewmodel,
    productList: List<ProductItem>,
    categoryList: List<CategoryItem>,
    innerPadding: PaddingValues) {

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .bitmapConfig(Bitmap.Config.RGB_565) // uses 2 bytes/pixel instead of 4
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.1) // Reduce memory usage
                .build()
        }.build()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
           /* .background(
                color = MaterialTheme.colorScheme.background
            )*/,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CardCarousel()
            ShowCategories(categoryList)
            ShowNewProducts()
        }

        items(productList, key= {it.id}) { it ->
            ShowProductItems(viewmodel, it, imageLoader)
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

        Spacer(modifier = Modifier.height(1.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(cardItems.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (isSelected) 10.dp else 5.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.Green else Color(0xFFFD78D8))
                ){}
            }
        }
    }
}

@Composable
fun ShowBanner(){
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource( R.color.card_bg_color),
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(top = 2.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(1.dp)){

            Row {
                Column {
                    Text(text = "GET 20% OFF",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White)
                    Text(text = "on SkinCare products",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "12-16 October",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black,
                        modifier = Modifier
                            .background(color = limeGreen, RoundedCornerShape(18.dp))
                            .padding(5.dp))
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(painter = painterResource(R.drawable.image_3),
                    modifier = Modifier
                        .height(100.dp)
                        .width(150.dp),
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
        modifier = Modifier.padding(top = 20.dp)){
        Text(headingName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(heading2Name,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
@Composable
fun ShowCategories(categoryList: List<CategoryItem>) {

    ShowHeading("Categories", "See All")
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        items(categoryList){ category->

            Column(modifier = Modifier.padding(3.dp),
                verticalArrangement = Arrangement.Center){

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.card_bg_color)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = category.imageUrl),
                        contentDescription = "",
                        contentScale = ContentScale.Fit, // so the image doesn't stretch
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp) // inner padding
                            .clip(CircleShape)
                    )
                }
                Text(category.categoryName,
                    textAlign = TextAlign.Center,
                 //   color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally))
            }

        }
    }
}

@Composable
fun ShowNewProducts() {
    ShowHeading("New Products", "See All")
}

@Composable
fun ShowProductItems(viewmodel: MainViewmodel, productItem : ProductItem, imageLoader: ImageLoader){

    val painter = rememberAsyncImagePainter(model = productItem.imageUrl)

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

            Card(modifier = Modifier.padding(5.dp)) {

                Box(modifier = Modifier.fillMaxSize()){
                    Column {
                        Row{
                            IconButton(
                                onClick = {
                                   viewmodel.updateFavorite(productItem)
                                }
                            ) {
                                Icon(imageVector = if (productItem.isFavorite) Icons.Rounded.Favorite
                                                         else  Icons.Rounded.FavoriteBorder,
                                    tint = if (productItem.isFavorite) Purple80 else Color.LightGray,
                                    contentDescription = "Favorite",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(color = colorResource(R.color.card_bg_color))
                                        .padding(10.dp))

                            }
                            Spacer(Modifier.weight(1f))

                            productItem.sellerCategory?.let{
                                ElevatedButton( colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.card_bg_color)),
                                    onClick = {}) {
                                    Text(text = it,
                                        color = limeGreen)
                                }
                            }

                        }

                        Column(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally){

                            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                                AsyncImage(
                                    model = productItem.imageUrl,
                                    contentDescription = "product_image",
                                    modifier = Modifier
                                        .width(220.dp)
                                        .height(140.dp)
                                        .padding(5.dp)
                                )
                            }

                            ProductItemCardContent(productItem, viewmodel)

                        }

                    }



                }
            }

    }
}

@Composable
fun ProductItemCardContent(productItem: ProductItem,
                           viewmodel: MainViewmodel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
        colors  = CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_bg_color)
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
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "${productItem.categoryName} \u2022 ${productItem.categoryName2}",
                color = MaterialTheme.colorScheme.primary
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
                    onRatingChanged = {
                        viewmodel.updateRating(it, productItem)
                    })

                Text(
                    "${productItem.reviewCount} Reviews",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Serif,
                    color = Purple80
                )
            }

        }
        IconButton(
            onClick = {

            },
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
                    .background(color = colorResource(R.color.card_bg_color))
                    .padding(10.dp)
            )

        }
    }
    }
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Int,
    onRatingChanged: (Int) -> Unit
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
                    .clickable(
                        //.selectable(
                        // selected = isSelected,
                        onClick = { onRatingChanged(i) }
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
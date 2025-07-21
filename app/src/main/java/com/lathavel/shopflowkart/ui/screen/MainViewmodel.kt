package com.lathavel.shopflowkart.ui.screen

import androidx.lifecycle.ViewModel
import com.lathavel.shopflowkart.R
import com.lathavel.shopflowkart.data.model.CategoryItem
import com.lathavel.shopflowkart.data.model.ProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewmodel: ViewModel() {
    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList : StateFlow<List<ProductItem>> = _productList

    private val _categoryList = MutableStateFlow<List<CategoryItem>>(emptyList())
    val categoryList : StateFlow<List<CategoryItem>> = _categoryList

    init{
        getCategories()
        getProducts()
    }

    private fun getCategories() {
        _categoryList.value = listOf<CategoryItem>(
            CategoryItem(
                categoryName = "Skincare",
                imageUrl = R.drawable.skin_care
            ),
            CategoryItem(
                categoryName = "Cleanser",
                imageUrl = R.drawable.lotion
            ),
            CategoryItem(
                categoryName = "Sun Cream",
                imageUrl = R.drawable.sun_cream
            ),
            CategoryItem(
                categoryName = "Toner",
                imageUrl = R.drawable.toner
            ),
            CategoryItem(
                categoryName = "Serums",
                imageUrl = R.drawable.serum
            ),
            CategoryItem(
                categoryName = "Gloss",
                imageUrl = R.drawable.gloss
            ),
            CategoryItem(
                categoryName = "Gel",
                imageUrl = R.drawable.gel
            )
        )
    }

    private fun getProducts_Duplicated(){

        val sharedProd1 = R.drawable.image_1
        val sharedProd2 = R.drawable.image_2
        val sharedProd3 = R.drawable.image_3
        
        _productList.value = (1..100).map {
            counter ->

            ProductItem(
                id = counter,
                brandName = "Clencera",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 5,
                imageUrl = if (counter%2== 0)  sharedProd1 else sharedProd2,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            )
        }
    }

    private fun getProducts() {

        val sharedProd1 = R.drawable.image_1
        val sharedProd2 = R.drawable.image_2
        val sharedProd3 = R.drawable.image_3
        val sharedProd4 = R.drawable.image_4
        val sharedProd5 = R.drawable.image_5
        val sharedProd6 = R.drawable.image_6
        val sharedProd7 = R.drawable.image_7
        val sharedProd8 = R.drawable.image_8
        val sharedProd9 = R.drawable.image_9

        _productList.value = listOf<ProductItem>(
            ProductItem(
                1,
                brandName = "Clencera",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 5,
                imageUrl = sharedProd2,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                2,
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 2,
                imageUrl = sharedProd3,
                reviewCount = 2045,
                sellerCategory = null

        ),
            ProductItem(
                3,
                brandName = "CalmSkin",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 1,
                imageUrl = sharedProd5,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                4,
                brandName = "Glow",
                productName = "Foaming Facial Cleanser",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Cleanser",
                actualValue = 999f,
                discountValue = 849f,
                rating = 4,
                imageUrl = sharedProd9,
                reviewCount = 954,
                sellerCategory = "Top Seller"
            ),
            ProductItem(
                5,
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 0,
                imageUrl = sharedProd6,
                reviewCount = 2045,
                sellerCategory = null

            ),
            ProductItem(
                id = 10,
                brandName = "CalmSkin",
                productName = "HydraBalance Refreshing Toner",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Toner & Pore Care",
                actualValue = 699f,
                discountValue = 575f,
                rating = 5,
                imageUrl = sharedProd1,
                reviewCount = 645,
                sellerCategory = "Pore Minimizer"
            ),
            ProductItem(
                id = 14,
                brandName = "CalmSkin",
                productName = "Radiance Boost Vitamin C Serum",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum & Treatment",
                actualValue = 1099f,
                discountValue = 899f,
                rating = 5,
                imageUrl = sharedProd8, // Replace with your actual drawable
                reviewCount = 1562,
                sellerCategory = ""
            ),

            ProductItem(
                6, "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 0,
                imageUrl = sharedProd3,
                reviewCount = 2045,
                sellerCategory = null

            ),
            ProductItem(
                id = 7,
                brandName = "CalmSkin",
                productName = "Deep Renewal Night Moisturizer",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Moisturizer",
                actualValue = 999f,
                discountValue = 825f,
                rating = 4,
                imageUrl = sharedProd4, // Replace with actual drawable if needed
                reviewCount = 978,
                sellerCategory = "Night Care"
            ),

            ProductItem(
                id = 8,
                brandName = "CalmSkin",
                productName = "Daily Radiance Day Moisturizer",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Moisturizer",
                actualValue = 899f,
                discountValue = 749f,
                rating = 5,
                imageUrl = sharedProd7, // Replace with your actual drawable
                reviewCount = 1340,
                sellerCategory = "Daily Essentials"
            ),

            ProductItem(
                id = 9,
                brandName = "CalmSkin",
                productName = "Styling Hair Gel",
                isFavorite = false,
                isInStock = true,
                categoryName = "Hair Care",
                categoryName2 = "Hair Gel",
                actualValue = 499f,
                discountValue = 399f,
                rating = 4,
                imageUrl = sharedProd6,
                reviewCount = 876,
                sellerCategory = "Unisex"
            ))
    }

    fun updateFavorite(productItem: ProductItem) {
        _productList.value = _productList.value.map { product ->
            if (product.id == productItem.id) {
                product.copy(isFavorite = !product.isFavorite)
            } else {
                product
            }
        }

    }

    fun updateRating(ratingValue: Int, productItem: ProductItem) {
        _productList.value = _productList.value.map { product ->
            if (product.id == productItem.id) {
                product.copy(rating = ratingValue)
            } else {
                product
            }
        }

    }

}
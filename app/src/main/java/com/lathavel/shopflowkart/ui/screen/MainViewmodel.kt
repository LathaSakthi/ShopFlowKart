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
                imageUrl = R.drawable.categorysample
            ),
            CategoryItem(
                categoryName = "Cleanser",
                imageUrl = R.drawable.product2
            ),
            CategoryItem(
                categoryName = "Toner",
                imageUrl = R.drawable.product3
            ),
            CategoryItem(
                categoryName = "Serums",
                imageUrl = R.drawable.categorysample
            ),
            CategoryItem(
                categoryName = "Moisturisers",
                imageUrl = R.drawable.product2
            ),
            CategoryItem(
                categoryName = "Gel",
                imageUrl = R.drawable.product3
            )
        )
    }

    private fun getProducts() {
        _productList.value = listOf<ProductItem>(
            ProductItem(
                brandName = "Clencera",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 4.5f,
                imageUrl = R.drawable.product1,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                brandName = "Glow",
                productName = "Foaming Facial Cleanser",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Cleanser",
                actualValue = 999f,
                discountValue = 849f,
                rating = 4.8f,
                imageUrl = R.drawable.product2,
                reviewCount = 954,
                sellerCategory = "Top Seller"
            ),
            ProductItem(
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 4.3f,
                imageUrl = R.drawable.product3,
                reviewCount = 2045,
                sellerCategory = null

        ),
            ProductItem(
                brandName = "Clencera",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 4.5f,
                imageUrl = R.drawable.product1,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                brandName = "Glow",
                productName = "Foaming Facial Cleanser",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Cleanser",
                actualValue = 999f,
                discountValue = 849f,
                rating = 4.8f,
                imageUrl = R.drawable.product2,
                reviewCount = 954,
                sellerCategory = "Top Seller"
            ),
            ProductItem(
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 4.3f,
                imageUrl = R.drawable.product3,
                reviewCount = 2045,
                sellerCategory = null

            ))
    }

}
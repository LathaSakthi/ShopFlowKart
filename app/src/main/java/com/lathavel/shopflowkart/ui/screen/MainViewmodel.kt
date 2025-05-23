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
                imageUrl = R.drawable.product1
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
                imageUrl = R.drawable.product1
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
                imageUrl = R.drawable.product1,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                2,
                brandName = "Glow",
                productName = "Foaming Facial Cleanser",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Cleanser",
                actualValue = 999f,
                discountValue = 849f,
                rating = 5,
                imageUrl = R.drawable.product2,
                reviewCount = 954,
                sellerCategory = "Top Seller"
            ),
            ProductItem(
                3,
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 2,
                imageUrl = R.drawable.product3,
                reviewCount = 2045,
                sellerCategory = null

        ),
            ProductItem(
                4,
                brandName = "Clencera",
                productName = "Niacinamide 10% + Zinc 1%",
                isFavorite = true,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Serum",
                actualValue = 750f,
                discountValue = 599f,
                rating = 1,
                imageUrl = R.drawable.product1,
                reviewCount = 1123,
                sellerCategory = "Dermatologist Recommended"
            ),
            ProductItem(
                5,
                brandName = "Glow",
                productName = "Foaming Facial Cleanser",
                isFavorite = false,
                isInStock = true,
                categoryName = "Skincare",
                categoryName2 = "Cleanser",
                actualValue = 999f,
                discountValue = 849f,
                rating = 4,
                imageUrl = R.drawable.product2,
                reviewCount = 954,
                sellerCategory = "Top Seller"
            ),
            ProductItem(
                6,
                brandName = "AfterGlow",
                productName = "Hydro Boost Water Gel",
                isFavorite = true,
                isInStock = false,
                categoryName = "Moisturizer",
                categoryName2 = "Gel",
                actualValue = 950f,
                discountValue = 825f,
                rating = 0,
                imageUrl = R.drawable.product3,
                reviewCount = 2045,
                sellerCategory = null

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
package com.lathavel.shopflowkart.data.model;

data class ProductItem (
    val id: Int,
    val brandName: String,
    val productName: String,
    var isFavorite: Boolean,
    val isInStock: Boolean,
    val categoryName: String,
    val categoryName2: String,
    val actualValue: Float,
    val discountValue: Float,
    var rating: Int,
    val imageUrl: Int,
    val reviewCount: Int,
    val sellerCategory: String?
    )
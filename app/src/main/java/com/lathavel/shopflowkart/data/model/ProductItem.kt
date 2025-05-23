package com.lathavel.shopflowkart.data.model;

data class ProductItem (
    val brandName: String,
    val productName: String,
    val isFavorite: Boolean,
    val isInStock: Boolean,
    val categoryName: String,
    val categoryName2: String,
    val actualValue: Float,
    val discountValue: Float,
    val rating: Float,
    val imageUrl: Int,
    val reviewCount: Int,
    val sellerCategory: String?
    )
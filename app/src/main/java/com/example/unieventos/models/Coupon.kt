package com.example.unieventos.models

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 * @property id the id of the coupon
 * @property name the name of the coupon
 * @property code the code of the coupon
 * @property discount the discount of the coupon
 * @property imageUrl the image url of the coupon
 * @property expirationDate the expiration date of the coupon
 */
data class Coupon(
    val id: Int,
    val name: String,
    val code: String,
    val discount: Int,
    val imageUrl: String,
    val expirationDate: String
)

package com.example.unieventos.models

data class Coupon(
    var id: String = "",
    val name: String = "",
    val code: String = "",
    val discount: Int = 0,
    val expirationDate: String = "",
)

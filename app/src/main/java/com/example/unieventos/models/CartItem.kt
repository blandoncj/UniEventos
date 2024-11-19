package com.example.unieventos.models

data class CartItem(
    val id: String = "",
    val eventId: String = "",
    val eventName: String = "",
    val eventPosterImage: String = "",
    val localityName: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0
)

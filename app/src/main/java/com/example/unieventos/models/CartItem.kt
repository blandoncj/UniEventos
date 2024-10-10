package com.example.unieventos.models

data class CartItem(
    val id: Int,
    val eventId: Int,
    val eventName: String,
    val eventPosterImage: String,
    val localityName: String,
    val quantity: Int,
)

package com.example.unieventos.models

data class CartItem(
    val id: Int,
    val eventId: Int,
    val eventName: String,
    val eventImage: String,
    val quantity: Int,
)

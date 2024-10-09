package com.example.unieventos.models

data class Event(
    val id: Int,
    val name: String,
    val city: String,
    val address: String,
    val description: String,
    val date: String,
    val category: String,
    val imageUrl: String,
    val locations: List<Any>
)

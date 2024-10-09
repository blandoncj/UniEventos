package com.example.unieventos.models

import com.example.unieventos.enums.Role

class Customer(
    val cedula: String,
    val city: String,
    val phone: String,
    id: Int,
    name: String,
    email: String,
    password: String
) : User(
    id, name, Role.CUSTOMER, email, password
)
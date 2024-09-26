package com.example.unieventos.models

import com.example.unieventos.enums.Role

data class User(
    val id: Int,
    val name: String,
    val role: Role,
    val email: String,
    val password: String
)
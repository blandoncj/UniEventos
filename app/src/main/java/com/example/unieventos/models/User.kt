package com.example.unieventos.models

import com.example.unieventos.enums.Role

data class User(
    var id: String = "",
    val role: Role = Role.CUSTOMER,
    val cedula: String = "",
    val name: String = "",
    val city: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val isActive: Boolean = true
)

package com.example.unieventos.models

import com.example.unieventos.enums.Role

/**
 * User class represents a user in the system.
 * @param id The user's id.
 * @param name The user's name.
 * @param role The user's role.
 * @param email The user's email.
 * @param password The user's password.
 */
open class User(
    val id: Int,
    val name: String,
    val role: Role,
    val email: String,
    val password: String
)
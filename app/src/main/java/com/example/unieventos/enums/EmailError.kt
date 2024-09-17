package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating an email.
 * @property EMPTY The email is empty.
 * @property INVALID_FORMAT The email has an invalid format.
 * @property ALREADY_REGISTERED The email is already registered.
 * @property NONE No error occurred.
 */
enum class EmailError {
    EMPTY,
    INVALID_FORMAT,
    ALREADY_REGISTERED,
    NONE
}
package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating a password.
 * @property EMPTY The password is empty.
 * @property INVALID_LENGTH The password has an invalid length.
 * @property INCORRECT The password is incorrect.
 * @property NONE There is no error.
 */
enum class PasswordError {
    EMPTY,
    INVALID_LENGTH,
    INCORRECT,
    NONE
}
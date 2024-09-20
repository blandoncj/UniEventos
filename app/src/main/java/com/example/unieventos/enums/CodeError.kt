package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating a code.
 * @property EMPTY The code is empty.
 * @property INVALID_LENGTH The code has an invalid length.
 * @property NOT_FOUND The code was not found.
 * @property NONE No errors occurred.
 */
enum class CodeError {
    EMPTY,
    INVALID_LENGTH,
    NOT_FOUND,
    NONE
}
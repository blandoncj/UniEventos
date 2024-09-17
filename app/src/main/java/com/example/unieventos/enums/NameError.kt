package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating a name.
 * @property EMPTY The name is empty.
 * @property INVALID_LENGTH The name has an invalid length.
 * @property INVALID_FORMAT The name has an invalid format.
 * @property NONE The name is valid.
 */
enum class NameError {
    EMPTY,
    INVALID_LENGTH,
    INVALID_FORMAT,
    NONE
}
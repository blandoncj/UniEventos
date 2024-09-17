package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating a phone number.
 * @property EMPTY The phone number is empty.
 * @property INVALID_LENGTH The phone number has an invalid length.
 * @property ALREADY_REGISTERED The phone number is already registered.
 * @property NONE No error occurred.
 */
enum class PhoneError {
    EMPTY,
    INVALID_LENGTH,
    ALREADY_REGISTERED,
    NONE
}
package com.example.unieventos.enums

/**
 * Enum class that represents the possible errors that can occur when validating a cedula.
 * @property EMPTY The cedula is empty.
 * @property INVALID_LENGTH The cedula has an invalid length.
 * @property ALREADY_REGISTERED The cedula is already registered.
 * @property NONE No errors occurred.
 */
enum class CedulaError {
    EMPTY,
    INVALID_LENGTH,
    ALREADY_REGISTERED,
    NONE
}
package com.example.unieventos.utils

import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError

fun validateCedula(cedula: String): CedulaError {
    return when {
        cedula.isEmpty() -> CedulaError.EMPTY
        cedula.length != 10 && cedula.length != 8 -> CedulaError.INVALID_LENGTH
        cedula == "1234567890" -> CedulaError.ALREADY_REGISTERED
        else -> CedulaError.NONE
    }
}

fun validateName(name: String): NameError {
    return when {
        name.isEmpty() -> NameError.EMPTY
        name.length < 3 -> NameError.INVALID_LENGTH
        !name.matches(Regex("^[\\p{L} .'-]+$")) -> NameError.INVALID_FORMAT
        else -> NameError.NONE
    }
}

fun validateEmail(email: String): EmailError {
    return when {
        email.isEmpty() -> EmailError.EMPTY
        email == "jbc@gmail.com" -> EmailError.ALREADY_REGISTERED
        validateEmailFormat(email) == EmailError.INVALID_FORMAT -> EmailError.INVALID_FORMAT
        else -> EmailError.NONE
    }
}

fun validateEmailFormat(email: String): EmailError {
    return when {
        email.isEmpty() -> EmailError.EMPTY
        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> EmailError.INVALID_FORMAT
        else -> EmailError.NONE
    }
}

fun validatePasswordFormat(password: String): PasswordError {
    return when {
        password.isEmpty() -> PasswordError.EMPTY
        password.length < 8 -> PasswordError.INVALID_LENGTH
        else -> PasswordError.NONE
    }
}

fun validatePasswordsMatch(password: String, confirmPassword: String): PasswordError {
    return when {
        password != confirmPassword -> PasswordError.INCORRECT
        else -> PasswordError.NONE
    }
}

fun validatePhone(phone: String): PhoneError {
    return when {
        phone.isEmpty() -> PhoneError.EMPTY
        phone.length != 10 -> PhoneError.INVALID_LENGTH
        phone == "1234567890" -> PhoneError.ALREADY_REGISTERED
        else -> PhoneError.NONE
    }
}

fun validateFields(fields: List<String>): Boolean {
    return fields.all { it.isNotEmpty() }
}
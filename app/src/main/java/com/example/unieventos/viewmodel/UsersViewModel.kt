package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError
import com.example.unieventos.enums.Role
import com.example.unieventos.models.Customer
import com.example.unieventos.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel : ViewModel() {

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        _users.value = getUsers()
    }

    fun getUserById(id: Int): User? {
        return _users.value.find { it.id == id }
    }

    fun getUserByEmail(email: String): User? {
        return _users.value.find { it.email == email }
    }

    fun getCustomerByCedula(cedula: String): Customer? {
        return _users.value.filterIsInstance<Customer>().find { it.cedula == cedula }
    }

    fun getCustomerByPhone(phone: String): Customer? {
        return _users.value.filterIsInstance<Customer>().find { it.phone == phone }
    }

    fun createUser(user: User) {
        _users.value += user
    }

    fun updateUser(user: User) {
        val index = _users.value.indexOfFirst { it.id == user.id }
        if (index != -1) {
            _users.value = _users.value.toMutableList().apply {
                set(index, user)
            }
        }
    }

    fun deleteUser(user: User) {
        _users.value -= user
    }

    fun login(email: String, password: String): User? {
        return _users.value.find { it.email == email && it.password == password }
    }

    private fun getUsers(): List<User> {
        return listOf(
            User(
                1,
                "Juan",
                Role.ADMIN,
                "admin@eam.com",
                "123456"
            ),
            Customer(
                "1092850716",
                "Armenia",
                "3226843150",
                2,
                "Jacobo",
                "customer@eam.com",
                "123456"
            ),
        )
    }

    fun validateCedula(cedula: String): CedulaError {
        return when {
            cedula.isEmpty() -> CedulaError.EMPTY
            cedula.length != 10 && cedula.length != 8 -> CedulaError.INVALID_LENGTH
            cedula == getCustomerByCedula(cedula)?.cedula -> CedulaError.ALREADY_REGISTERED
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

    fun validatePhone(phone: String): PhoneError {
        return when {
            phone.isEmpty() -> PhoneError.EMPTY
            phone.length != 10 -> PhoneError.INVALID_LENGTH
            phone == getCustomerByPhone(phone)?.phone -> PhoneError.ALREADY_REGISTERED
            else -> PhoneError.NONE
        }
    }

    fun validateEmail(email: String): EmailError {
        return when {
            email.isEmpty() -> EmailError.EMPTY
            email == getUserByEmail(email)?.email -> EmailError.ALREADY_REGISTERED
            validateEmailFormat(email) == EmailError.INVALID_FORMAT -> EmailError.INVALID_FORMAT
            else -> EmailError.NONE
        }
    }

    fun validateEmailFormat(email: String): EmailError {
        return when {
            email.isEmpty() -> EmailError.EMPTY
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> EmailError.INVALID_FORMAT

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

    fun validateFields(fields: List<String>): Boolean {
        return fields.all { it.isNotEmpty() }
    }

}
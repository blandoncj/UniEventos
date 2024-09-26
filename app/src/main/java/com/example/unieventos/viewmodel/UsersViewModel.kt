package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unieventos.enums.Role
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
            User(
                2,
                "Jacobo",
                Role.CUSTOMER,
                "customer@eam.com",
                "123456"
            )
        )
    }

}
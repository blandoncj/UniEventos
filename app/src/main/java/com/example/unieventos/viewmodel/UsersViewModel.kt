package com.example.unieventos.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError
import com.example.unieventos.models.User
import com.example.unieventos.utils.RequestResult
import com.example.unieventos.utils.SharedPreferencesUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UsersViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val db = Firebase.firestore
    private val _authResult = MutableStateFlow<RequestResult?>(null)

    val authResult: StateFlow<RequestResult?> = _authResult.asStateFlow()
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        auth.currentUser?.uid?.let { uid ->
            viewModelScope.launch { _currentUser.value = getUserById(uid) }
        }
    }

    private suspend fun createUserFirebase(user: User) {
        val response = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val userId = response.user?.uid ?: throw Exception("No se pudo crear el usuario")

        val userSave = user.copy(id = userId, password = "")
        db.collection("users").document(userId).set(userSave).await()
    }

    fun createUser(user: User) {
        viewModelScope.launch {
            _authResult.value = RequestResult.Loading
            _authResult.value = runCatching { createUserFirebase(user) }
                .fold(
                    onSuccess = { RequestResult.Success("Usuario creado exitosamente") },
                    onFailure = { handleAuthError(it) }
                )
        }
    }

    private fun handleAuthError(e: Throwable): RequestResult.Error {
        val errorMessage = when (e) {
            is FirebaseAuthException -> {
                when (e.errorCode) {
                    "ERROR_INVALID_EMAIL" -> "Correo inválido"
                    "ERROR_WRONG_PASSWORD" -> "Contraseña incorrecta"
                    "ERROR_EMAIL_ALREADY_IN_USE" -> "Correo ya registrado"
                    else -> "Error al crear el usuario ${e.message}"
                }
            }
            else -> "Error al crear el usuario ${e.message}"
        }
        return RequestResult.Error(errorMessage)
    }

    fun resetAuthResult() {
        _authResult.value = null
    }

    private suspend fun deleteUserFirebase(userId: String) {
        db.collection("users").document(userId).delete().await()
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch {
            _authResult.value = RequestResult.Loading
            _authResult.value = runCatching { deleteUserFirebase(userId) }
                .fold(
                    onSuccess = { RequestResult.Success("Usuario eliminado exitosamente") },
                    onFailure = { RequestResult.Error(it.toString()) }
                )
        }
    }

    private suspend fun loginFirebase(email: String, password: String) {
        val response = auth.signInWithEmailAndPassword(email, password).await()
        val userId = response.user?.uid ?: throw Exception("No se pudo iniciar sesión")

        val user = getUserById(userId)
        _currentUser.value = user
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if(_currentUser.value != null) {
                _currentUser.value = null
            }
            _authResult.value = RequestResult.Loading
            _authResult.value = runCatching { loginFirebase(email, password) }
                .fold(
                    onSuccess = { RequestResult.Success("Sesión iniciada exitosamente") },
                    onFailure = { handleAuthError(it) }
                )
        }
    }

    suspend fun getUsers(): List<User> {
        val snapshot =
            db
                .collection("users")
                .get()
                .await()

        return snapshot.documents.mapNotNull {
            val user = it.toObject(User::class.java)
            requireNotNull(user)
            user.id = it.id
            user
        }
    }

    suspend fun getUserById(id: String): User? {
        val snapshot =
            db
                .collection("users")
                .document(id)
                .get()
                .await()

        val user = snapshot.toObject(User::class.java)
        user?.id = snapshot.id
        return user
    }

}

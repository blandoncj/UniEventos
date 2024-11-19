package com.example.unieventos.utils

sealed class RequestResult {
    data object Loading : RequestResult()
    data class Success(val message: String) : RequestResult()
    data class Error(val errorMessage: String) : RequestResult()
}
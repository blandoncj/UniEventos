package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unieventos.models.Order
import com.example.unieventos.utils.RequestResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class OrdersViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _orders = MutableStateFlow(emptyList<Order>())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    private val _orderResult = MutableStateFlow<RequestResult?>(null)
    val orderResult: StateFlow<RequestResult?> = _orderResult.asStateFlow()

    private suspend fun getOrdersFirebase(): List<Order> {
        val snapshot = db.collection("orders").get().await()

        return snapshot.documents.mapNotNull {
            it.toObject(Order::class.java)?.apply {
                this.id = it.id
            }
        }
    }

    private suspend fun createOrderFirebase(order: Order) {
        db.collection("orders").add(order).await()
    }

    fun createOrder(order: Order) {
        viewModelScope.launch {
            _orderResult.value = RequestResult.Loading
            _orderResult.value = runCatching { createOrderFirebase(order) }
                .fold(
                    onSuccess = { RequestResult.Success("Orden creada exitosamente") },
                    onFailure = { RequestResult.Error("Error al crear la orden") }
                )
        }
    }

    fun resetOrderResult() {
        _orderResult.value = null
    }

}
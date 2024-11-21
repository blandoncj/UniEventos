package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unieventos.models.Order
import com.example.unieventos.utils.RequestResult
import com.google.firebase.auth.FirebaseAuth
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

    init {
        viewModelScope.launch {
            val userId = getCurrentUserId()
            if (userId != null) {
                _orders.value = getOrdersFirebase(userId)
            } else {
                _orders.value = emptyList()
            }
        }
    }

    private suspend fun getOrdersFirebase(userId: String): List<Order> {
        val snapshot = db.collection("orders")
            .whereEqualTo("userId", userId)
            .get().await()

        return snapshot.documents.mapNotNull {
            it.toObject(Order::class.java)?.apply {
                this.id = it.id
            }
        }
    }

    private suspend fun createOrderInFirebase(order: Order) {
        val orderRef = db.collection("orders").add(order).await()

        db.collection("users")
            .document(order.userId)
            .collection("history")
            .document(orderRef.id)
            .set(order)
            .await()
    }

    fun createOrder(order: Order) {
        viewModelScope.launch {
            _orderResult.value = RequestResult.Loading
            try {
                createOrderInFirebase(order)
                _orders.value = getOrdersFirebase(order.userId)
                _orderResult.value = RequestResult.Success("Order created successfully!")
            } catch (e: Exception) {
                _orderResult.value = RequestResult.Error(e.message ?: "Error creating order")
            }
        }
    }

    fun resetOrderResult() {
        _orderResult.value = null
    }

    private fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }
}
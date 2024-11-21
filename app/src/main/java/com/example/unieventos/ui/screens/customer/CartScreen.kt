package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unieventos.ui.components.cart.CartItemView
import com.example.unieventos.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    paddingValues: PaddingValues
) {
    val cartItems = cartViewModel.events.collectAsState().value

    if (cartItems.isEmpty()) {
        Text(
            text = "El carrito está vacío",
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(cartItems) { item ->
                CartItemView(
                    cartItem = item,
                    cartViewModel = cartViewModel
                )
            }
        }
    }


}
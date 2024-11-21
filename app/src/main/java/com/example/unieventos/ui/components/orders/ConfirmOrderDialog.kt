package com.example.unieventos.ui.components.orders

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.models.Order
import com.example.unieventos.models.OrderItem
import com.example.unieventos.ui.components.AlertMessage
import com.example.unieventos.ui.components.AlertType
import com.example.unieventos.utils.RequestResult
import com.example.unieventos.utils.SharedPreferencesUtils
import com.example.unieventos.viewmodel.CartViewModel
import com.example.unieventos.viewmodel.OrdersViewModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmOrderDialog(
    cartViewModel: CartViewModel,
    ordersViewModel: OrdersViewModel = OrdersViewModel(),
    onDismiss: () -> Unit,
    onViewHistory: () -> Unit
) {
    val orderResult by ordersViewModel.orderResult.collectAsState()
    val orders by ordersViewModel.orders.collectAsState()
    val context = LocalContext.current
    val userId = SharedPreferencesUtils.getCurrentUser(context)?.id

    if (userId == null) {
        AlertMessage(
            type = AlertType.ERROR,
            message = stringResource(id = R.string.error_user_not_found)
        )
        return
    }

    val items = cartViewModel.getItemsCart().map {
        OrderItem(
            eventId = it.eventId,
            quantity = it.quantity,
            price = it.price,
            localityName = it.localityName,
        )
    }

    var showDiscount by remember { mutableStateOf(false) }
    var coupon by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (items.isNotEmpty()) {
                        ordersViewModel.createOrder(
                            Order(
                                date = LocalDateTime.now().toString(),
                                total = cartViewModel.getTotal(),
                                userId = userId,
                                items = items
                            )
                        )
                        cartViewModel.clearCart()
                    } else {
                        Log.d("ConfirmOrderDialog", "Cart is empty")
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.confirm_btn))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel_btn))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.order_summary_lbl))
        },
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = coupon,
                        onValueChange = { coupon = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.7f),
                        placeholder = { Text(text = stringResource(id = R.string.coupons_lbl)) }
                    )
                    TextButton(
                        onClick = { showDiscount = !showDiscount }
                    ) {
                        Text(text = stringResource(id = R.string.apply_btn))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (showDiscount) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Subtotal: ${cartViewModel.getTotal()}",
                        style = MaterialTheme.typography.bodyLarge,
                        textDecoration = TextDecoration.LineThrough,
                        textAlign = TextAlign.End
                    )
                } else {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Subtotal: ${cartViewModel.getTotal()}",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (orderResult) {
                    is RequestResult.Loading -> {
                        LinearProgressIndicator()
                    }
                    is RequestResult.Success -> {
                        AlertMessage(
                            type = AlertType.SUCCESS,
                            message = stringResource(id = R.string.success_order_message),
                        )

                        LaunchedEffect(Unit) {
                            delay(3000)
                            ordersViewModel.resetOrderResult()
                            onDismiss()
                        }
                    }
                    is RequestResult.Error -> {
                        AlertMessage(
                            type = AlertType.ERROR,
                            message = (orderResult as RequestResult.Error).errorMessage,
                        )

                        LaunchedEffect(Unit) {
                            delay(3000)
                            ordersViewModel.resetOrderResult()
                        }
                    }

                    null -> {
//                        Text("Cargando...")
                    }
                }

                if (orders.isNotEmpty()) {
                    TextButton(onClick = onViewHistory) {
                        Text(text = stringResource(id = R.string.view_order_history))
                    }
                }
            }
        }
    )
}

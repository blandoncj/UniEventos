package com.example.unieventos.ui.components.orders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unieventos.R
import com.example.unieventos.models.Order
import com.example.unieventos.viewmodel.OrdersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderDialog(
    ordersViewModel: OrdersViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onDismiss: () -> Unit
) {
    val orders = ordersViewModel.orders.collectAsState(initial = emptyList())
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close_btn))
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.order_history_lbl),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orders.value.size) { index ->
                    val order = orders.value.getOrNull(index)
                    if (order != null) {
                        val isExpanded = expandedStates[order.id] ?: false

                        OrderCard(
                            order = order,
                            isExpanded = isExpanded,
                            onCardClick = {
                                expandedStates[order.id] = !isExpanded
                            }
                        )
                    }
                }
            }
        }
    )
}



@Composable
fun OrderCard(order: Order, isExpanded: Boolean, onCardClick: () -> Unit) {
    Card(
        onClick = onCardClick,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Order ID: ${order.id}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Total: $${order.total}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Date: ${order.date}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Text(
                        text = "Items: ${order.items.size}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Text(
                        text = "total: ${order.total}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer

                    )
                }
            }
        }
    }
}

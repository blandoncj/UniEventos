package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun CustomerEventDetailScreen(
    eventsViewModel: EventsViewModel,
    eventId: Int,
    onBack: () -> Unit
) {
    val event = eventsViewModel.getEventById(eventId)
    requireNotNull(event)


    Scaffold(
        topBar = {
            CustomTopAppBar(title = event.name, onBack = onBack)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(16.dp),
                containerColor = Color(0xFFF1E6F9)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to cart",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Black
                )
            }
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 30.dp),
        ) {
            Text(text = "Event Detail Screen")
            Text(text = "Event Name: ${event.name}")
        }
    }


}
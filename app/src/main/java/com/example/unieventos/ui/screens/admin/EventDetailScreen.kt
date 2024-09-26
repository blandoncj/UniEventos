package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unieventos.ui.components.events.EventDetailItem
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun EventDetailScreen(
    eventsViewModel: EventsViewModel,
    eventId: Int,
    onBack: () -> Unit
) {
    val event = eventsViewModel.getEventById(eventId)
    requireNotNull(event)

    Scaffold(
        topBar = {
            CustomTopAppBar(title = event.name, onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val model = ImageRequest.Builder(LocalContext.current)
                .data(event.imageUrl)
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = model,
                contentDescription = "Event Image",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                EventDetailItem(
                    imageVector = Icons.Default.CalendarMonth,
                    text = event.date
                )

                Spacer(modifier = Modifier.height(8.dp))

                EventDetailItem(
                    imageVector = Icons.Default.Place,
                    text = event.city
                )
            }
        }
    }
}


package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unieventos.R
import com.example.unieventos.ui.components.events.EventDetailItem
import com.example.unieventos.ui.components.events.EventForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun EventDetailScreen(
    eventsViewModel: EventsViewModel,
    eventId: Int,
    onBack: () -> Unit
) {
    val event = eventsViewModel.getEventById(eventId)

    if (event == null) {
        return
    }

    var name by rememberSaveable { mutableStateOf(event.name) }
    var city by rememberSaveable { mutableStateOf(event.city) }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var address by rememberSaveable { mutableStateOf(event.address) }
    var description by rememberSaveable { mutableStateOf(event.description) }
    var category by rememberSaveable { mutableStateOf(event.category) }
    var expandedCategory by rememberSaveable { mutableStateOf(false) }
    var date by rememberSaveable { mutableStateOf(event.date) }
    var isDatePicked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = event.name, onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 30.dp),
        ) {
            EventForm(
                name = name,
                onNameChange = { name = it },
                city = city,
                onCitySelected = { city = it },
                expandedCity = expandedCity,
                onExpandedCityChange = { expandedCity = it },
                address = address,
                onAddressChange = { address = it },
                description = description,
                onDescriptionChange = { description = it },
                category = category,
                onCategorySelected = { category = it },
                expandedCategory = expandedCategory,
                onExpandedCategoryChange = { expandedCategory = it },
                date = date,
                onDateChange = { date = it },
                isDatePicked = isDatePicked,
                onDatePickedChange = { isDatePicked = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                PrimaryButton(
                    text = stringResource(id = R.string.update_event_btn),
                    enabled = name.isNotEmpty() &&
                            city.isNotEmpty() &&
                            address.isNotEmpty() &&
                            description.isNotEmpty() &&
                            category.isNotEmpty() &&
                            date.isNotEmpty(),
                    onClick = {
                        val updatedEvent = event.copy(
                            eventId,
                            name = name,
                            city = city,
                            address = address,
                            description = description,
                            category = category,
                            date = date
                        )
                        eventsViewModel.updateEvent(updatedEvent)
                        onBack()
                    },
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SecondaryButton(
                    text = stringResource(id = R.string.delete_event_btn),
                    onClick = {
                        eventsViewModel.deleteEvent(event)
                        onBack()
                    }
                )
            }

        }
    }
}


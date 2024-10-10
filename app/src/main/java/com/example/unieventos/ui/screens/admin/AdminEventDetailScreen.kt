package com.example.unieventos.ui.screens.admin

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.unieventos.R
import com.example.unieventos.enums.DateError
import com.example.unieventos.ui.components.events.EventForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun AdminEventDetailScreen(
    eventsViewModel: EventsViewModel,
    eventId: Int,
    onBack: () -> Unit
) {
    val event = eventsViewModel.getEventById(eventId)

    if (event == null) {
        return
    }

    val context = LocalContext.current

    var name by rememberSaveable { mutableStateOf(event.name) }
    var city by rememberSaveable { mutableStateOf(event.city) }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var address by rememberSaveable { mutableStateOf(event.address) }
    var description by rememberSaveable { mutableStateOf(event.description) }
    var category by rememberSaveable { mutableStateOf(event.category) }
    var expandedCategory by rememberSaveable { mutableStateOf(false) }
    var date by rememberSaveable { mutableStateOf(event.date) }
    var isDatePicked by rememberSaveable { mutableStateOf(false) }
    var dateError by rememberSaveable { mutableStateOf(DateError.NONE) }
    var localities by rememberSaveable { mutableStateOf(event.localities.toMutableList()) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var posterImage by rememberSaveable { mutableStateOf<Uri?>(event.posterImage.let { Uri.parse(it) }) }
    var localitiesImage by rememberSaveable {
        mutableStateOf<Uri?>(event.localitiesImage.let {
            Uri.parse(
                it
            )
        })
    }


    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CustomTopAppBar(title = event.name, onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState),
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
                onDatePickedChange = {
                    isDatePicked = it
                    dateError = eventsViewModel.validateDate(date)
                },
                dateError = dateError,
                localities = localities,
                onLocalitiesChange = { localities = it },
                posterImage = posterImage,
                onPosterImageChange = { posterImage = it },
                localitiesImage = localitiesImage,
                onLocalitiesImageChange = { localitiesImage = it }
            )

            Spacer(modifier = Modifier.height(16.dp))


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
                            date = date,
                            localities = localities,
                            posterImage = posterImage.toString(),
                            localitiesImage = localitiesImage.toString()
                        )
                        eventsViewModel.updateEvent(updatedEvent)
                        Toast.makeText(
                            context,
                            R.string.event_updated,
                            Toast.LENGTH_SHORT
                        ).show()
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
                        showDialog = true
                    }
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        eventsViewModel.deleteEvent(event)
                        showDialog = false
                        Toast.makeText(
                            context,
                            R.string.event_deleted,
                            Toast.LENGTH_SHORT
                        ).show()
                        onBack()
                    }
                ) {
                    Text(stringResource(id = R.string.confirm_btn))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel_btn))
                }
            },
            title = {
                Text(stringResource(id = R.string.delete_event_title))
            },
            text = {
                Text(stringResource(id = R.string.delete_event_msg))
            }
        )
    }
}


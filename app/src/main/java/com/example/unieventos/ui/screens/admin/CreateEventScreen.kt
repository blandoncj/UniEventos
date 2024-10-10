package com.example.unieventos.ui.screens.admin

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import com.example.unieventos.R
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Event
import com.example.unieventos.models.Locality
import com.example.unieventos.ui.components.events.EventForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun CreateEventScreen(
    onBack: () -> Unit,
    eventsViewModel: EventsViewModel
) {
    var name by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var address by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var expandedCategory by rememberSaveable { mutableStateOf(false) }
    var date by rememberSaveable { mutableStateOf("") }
    var isDatePicked by rememberSaveable { mutableStateOf(false) }
    var dateError by rememberSaveable { mutableStateOf(DateError.NONE) }

    var posterImage by rememberSaveable { mutableStateOf<Uri?>(null) }
    var localitiesImage by rememberSaveable { mutableStateOf<Uri?>(null) }

    var localities by rememberSaveable { mutableStateOf(mutableListOf<Locality>()) }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    fun clearFields() {
        name = ""
        city = ""
        address = ""
        description = ""
        category = ""
        date = ""
        isDatePicked = false
        posterImage = null
        localitiesImage = null
        localities.clear()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.create_evt_lbl),
                onBack = onBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(10.dp))

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

            PrimaryButton(
                text = stringResource(id = R.string.create_event_btn),
                enabled = name.isNotEmpty() &&
                        city.isNotEmpty() &&
                        address.isNotEmpty() &&
                        description.isNotEmpty() &&
                        category.isNotEmpty() &&
                        date.isNotEmpty() &&
                        localities.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val event = Event(
                        id = 0,
                        name = name,
                        city = city,
                        address = address,
                        description = description,
                        date = date,
                        category = category,
                        localities = localities,
                        posterImage = posterImage.toString(),
                        localitiesImage = localitiesImage.toString()
                    )
                    eventsViewModel.createEvent(event)
                    Toast.makeText(
                        context,
                        context.getString(R.string.event_created),
                        Toast.LENGTH_SHORT
                    ).show()
                    clearFields()
                }
            )
        }
    }
}

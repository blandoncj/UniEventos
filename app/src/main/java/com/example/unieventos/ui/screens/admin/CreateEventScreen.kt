package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.ui.components.events.EventForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton

/**
 * Create event screen composable function.
 * This composable function displays the create event screen.
 * @param onBack The function that is called when the user presses the back button.
 */
@Composable
fun CreateEventScreen(
    onBack: () -> Unit,
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
                .padding(horizontal = 30.dp),
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
                onDatePickedChange = { isDatePicked = it }
            )

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                text = stringResource(id = R.string.create_event_btn),
                enabled = name.isNotEmpty() &&
                        city.isNotEmpty() &&
                        address.isNotEmpty() &&
                        description.isNotEmpty() &&
                        category.isNotEmpty() &&
                        date.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                onClick = { onBack() }
            )
        }
    }
}
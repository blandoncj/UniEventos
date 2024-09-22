package com.example.unieventos.ui.components.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.ui.components.CityField
import com.example.unieventos.ui.components.utils.DateField

/**
 * A composable that displays a form for the user to input their event information.
 * @param name The name that the user has input.
 * @param onNameChange A lambda that is called when the user changes the name.
 * @param city The city that the user has selected.
 * @param onCitySelected A lambda that is called when the user selects a city.
 * @param expandedCity The state of the city field.
 * @param onExpandedCityChange A lambda that is called when the user expands the city field.
 * @param address The address that the user has input.
 * @param onAddressChange A lambda that is called when the user changes the address.
 * @param description The description that the user has input.
 * @param onDescriptionChange A lambda that is called when the user changes the description.
 * @param date The date that the user has input.
 * @param onDateChange A lambda that is called when the user changes the date.
 * @param isDatePicked The state of the date field.
 * @param onDatePickedChange A lambda that is called when the user picks a date.
 */
@Composable
fun EventForm(
    name: String,
    onNameChange: (String) -> Unit,
    city: String,
    onCitySelected: (String) -> Unit,
    expandedCity: Boolean,
    onExpandedCityChange: (Boolean) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    category: String,
    onCategorySelected: (String) -> Unit,
    expandedCategory: Boolean,
    onExpandedCategoryChange: (Boolean) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    isDatePicked: Boolean,
    onDatePickedChange: (Boolean) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(id = R.string.nam_lbl)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        CityField(
            city = city,
            expanded = expandedCity,
            onExpandedChange = { onExpandedCityChange(it) },
            onCitySelected = onCitySelected,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = address,
            onValueChange = onAddressChange,
            label = { Text(text = stringResource(id = R.string.address_lbl)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = stringResource(id = R.string.desc_lbl)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        CategoryField(
            category = category,
            onCategorySelected = onCategorySelected,
            expanded = expandedCategory,
            onExpandedChange = { onExpandedCategoryChange(it) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        DateField(
            date = date,
            onDateChange = onDateChange,
            isDatePicked = isDatePicked,
            onDatePickedChange = onDatePickedChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
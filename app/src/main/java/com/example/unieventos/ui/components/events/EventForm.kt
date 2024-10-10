package com.example.unieventos.ui.components.events

import ImagePicker
import LocalityField
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Locality
import com.example.unieventos.ui.components.CityField
import com.example.unieventos.ui.components.utils.DateField

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
    dateError: DateError,
    localities: MutableList<Locality>,
    onLocalitiesChange: (MutableList<Locality>) -> Unit,
    posterImage: Uri?,
    onPosterImageChange: (Uri) -> Unit,
    localitiesImage: Uri?,
    onLocalitiesImageChange: (Uri) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(id = R.string.nam_lbl)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
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
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = stringResource(id = R.string.desc_lbl)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
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
            modifier = Modifier.fillMaxWidth(),
            dateError = dateError
        )

        LocalityField(
            localities = localities,
            onLocalitiesChange = onLocalitiesChange
        )

        Spacer(modifier = Modifier.height(10.dp))

        localities.forEach { locality ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Localidad: ${locality.name}, Aforo: ${locality.capacity}, Precio: ${locality.price}")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        ImagePicker(
            label = "Imagen poster",
            imageUri = posterImage,
            onImagePicked = onPosterImageChange
        )

        Spacer(modifier = Modifier.height(10.dp))

        ImagePicker(
            label = "Imagen localidades",
            imageUri = localitiesImage,
            onImagePicked = onLocalitiesImageChange
        )

        Spacer(modifier = Modifier.height(10.dp))

    }
}

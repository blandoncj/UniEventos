package com.example.unieventos.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R

/**
 * A city field that shows a list of cities in a dropdown menu.
 * @param city The selected city.
 * @param modifier The modifier to be applied to the city field.
 * @param expanded The state of the dropdown menu.
 * @param onExpandedChange The callback to change the state of the dropdown menu.
 * @param onCitySelected The callback to select a city.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityField(
    city: String,
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCitySelected: (String) -> Unit
) {
    val cities =
        listOf(
            "Armenia", "Bogotá", "Cali",
            "Medellín", "Pereira", "Manizales",
            "Cartagena", "Barranquilla", "Santa Marta",
            "Cúcuta", "Villavicencio", "Ibagué",
            "Neiva", "Pasto", "Popayán",
            "Tunja", "Riohacha", "Quibdó",
            "Montería", "Sincelejo", "Valledupar",
            "Arauca", "Yopal", "Mocoa",
            "Puerto Carreño", "San José del Guaviare", "Mitú",
            "Leticia"
        )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(!expanded) },
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            label = { Text(text = stringResource(id = R.string.city_lbl)) },
            placeholder = { Text(text = stringResource(id = R.string.city_placeholder)) },
            modifier = Modifier
                .menuAnchor()
                .then(modifier)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            cities.forEach() { city ->
                DropdownMenuItem(
                    text = { Text(text = city) },
                    onClick = {
                        onCitySelected(city)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}
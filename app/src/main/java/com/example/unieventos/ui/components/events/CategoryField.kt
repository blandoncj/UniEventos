package com.example.unieventos.ui.components.events

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
 * A category field that shows a list of categories in a dropdown menu.
 * @param category The selected category.
 * @param onCategorySelected The callback to select a category.
 * @param expanded The state of the dropdown menu.
 * @param onExpandedChange The callback to change the state of the dropdown menu.
 * @param modifier The modifier to be applied to the category field.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryField(
    category: String,
    onCategorySelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val categories =
        listOf(
            "Conferencia",
            "Concierto",
            "Deportes",
            "Fiesta",
            "Cine",
            "Teatro",
            "Festival",
            "Exposición",
            "Taller",
            "Curso",
            "Otro"
        )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(!expanded) },
    ) {
        OutlinedTextField(
            value = category,
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            label = { Text(text = stringResource(id = R.string.cat_lbl)) },
            placeholder = { Text(text = stringResource(id = R.string.cat_ph)) },
            modifier = Modifier
                .menuAnchor()
                .then(modifier)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            categories.forEach() { category ->
                DropdownMenuItem(
                    text = { Text(text = category) },
                    onClick = {
                        onCategorySelected(category)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}
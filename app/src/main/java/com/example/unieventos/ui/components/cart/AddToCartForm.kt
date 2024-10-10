package com.example.unieventos.ui.components.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.unieventos.models.Locality

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCartForm(
    locality: Locality,
    onLocalitySelected: (Locality) -> Unit,
    localities: List<Locality>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Column {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { onExpandedChange(!expanded) },
        ) {
            OutlinedTextField(
                value = locality.name,
                onValueChange = { },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                label = { Text(text = "Localidad") },
                placeholder = { Text(text = "Seleccione una localidad") },
                modifier = Modifier
                    .menuAnchor()
                    .then(modifier)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                localities.forEach { locality ->
                    DropdownMenuItem(
                        text = { Text(locality.name) },
                        onClick = {
                            onLocalitySelected(locality)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = quantity.toString(),
            onValueChange = {
                if (it.isNotEmpty()) {
                    onQuantityChange(it.toInt())
                }
            },
            label = { Text(text = "Cantidad") },
            placeholder = { Text(text = "Ingrese la cantidad") },
            modifier = modifier,
            isError = quantity > locality.capacity,
            supportingText = {
                if (quantity > locality.capacity) {
                    Text("La cantidad no puede ser superior al aforo")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocalityField() {
    // Lista que contiene las localidades, aforos y precios
    var locationsList by rememberSaveable { mutableStateOf(mutableListOf<Triple<String, String, String>>()) }

    var locality by rememberSaveable { mutableStateOf("") }
    var capacity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        locationsList.forEach { location ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Localidad: ${location.first}, Aforo: ${location.second}, Precio: ${location.third}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            OutlinedTextField(
                value = locality,
                onValueChange = { locality = it },
                label = { Text(text = "Localidad") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = capacity,
                onValueChange = { capacity = it },
                label = { Text(text = "Aforo") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text(text = "Precio") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (locality.isNotEmpty() && capacity.isNotEmpty() && price.isNotEmpty()) {
                        locationsList.add(Triple(locality, capacity, price))

                        locality = ""
                        capacity = ""
                        price = ""
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp),
                enabled = locality.isNotEmpty() && capacity.isNotEmpty() && price.isNotEmpty()
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    }
}

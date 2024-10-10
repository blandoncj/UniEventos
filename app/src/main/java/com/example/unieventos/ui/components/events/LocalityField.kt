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
import com.example.unieventos.models.Locality

@Composable
fun LocalityField(
    localities: MutableList<Locality>,
    onLocalitiesChange: (MutableList<Locality>) -> Unit
) {
    var localityName by rememberSaveable { mutableStateOf("") }
    var capacity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            OutlinedTextField(
                value = localityName,
                onValueChange = { localityName = it },
                label = { Text(text = "Localidad") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
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
                    .padding(end = 8.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text(text = "Precio") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (localityName.isNotEmpty() && capacity.isNotEmpty() && price.isNotEmpty()) {
                        val newLocality = Locality(
                            id = localities.size,
                            name = localityName,
                            capacity = capacity.toInt(),
                            price = price.toDouble()
                        )

                        val updatedLocalities = localities.toMutableList().apply {
                            add(newLocality)
                        }
                        onLocalitiesChange(updatedLocalities)
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp),
                enabled = localityName.isNotEmpty() && capacity.isNotEmpty() && price.isNotEmpty()
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    }
}

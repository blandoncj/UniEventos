package com.example.unieventos.ui.screens.customer

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.unieventos.models.CartItem
import com.example.unieventos.models.Locality
import com.example.unieventos.ui.components.cart.AddToCartForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.viewmodel.CartViewModel
import com.example.unieventos.viewmodel.EventsViewModel

@Composable
fun CustomerEventDetailScreen(
    eventsViewModel: EventsViewModel,
    eventId: Int,
    cartViewModel: CartViewModel,
    onBack: () -> Unit
) {
    var locality by remember { mutableStateOf<Locality?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(1) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val event = eventsViewModel.getEventById(eventId)
    requireNotNull(event)

    Scaffold(
        topBar = {
            CustomTopAppBar(title = event.name, onBack = onBack)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to cart",
                    modifier = Modifier.size(35.dp),
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = event.posterImage,
                contentDescription = "Event image",
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier.padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Información del Evento",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Event,
                            contentDescription = "Event Date",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(event.date)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Event Location",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${event.city}, ${event.address}")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Event Category",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(event.category)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(event.description)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                model = event.localitiesImage,
                contentDescription = "Localities image",
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier.padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Localidades",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Nombre")
                        Text("Aforo")
                        Text("Precio")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    event.localities.forEach { locality ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(locality.name)
                            Text("${locality.capacity} personas")
                            Text("${locality.price} USD")
                        }
                    }
                }
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
                        cartViewModel.addToCart(
                            CartItem(
                                id = 0,
                                eventId = event.id,
                                eventName = event.name,
                                eventPosterImage = event.posterImage,
                                localityName = locality?.name ?: event.localities[0].name,
                                quantity = quantity
                            )
                        )
                        showDialog = false
                        Toast.makeText(context, "Evento añadido al carrito", Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Añadir al carrito") },
            text = {
                AddToCartForm(
                    locality = locality ?: event.localities[0],
                    onLocalitySelected = { locality = it },
                    localities = event.localities,
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    quantity = quantity,
                    onQuantityChange = { quantity = it }
                )
            }
        )
    }
}

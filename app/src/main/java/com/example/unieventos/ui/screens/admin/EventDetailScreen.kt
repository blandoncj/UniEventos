package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unieventos.ui.components.utils.CustomTopAppBar

@Composable
fun EventDetailScreen(
    eventId: Int,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Event Detail", onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val model = ImageRequest.Builder(LocalContext.current)
                .data("https://loremflickr.com/400/400/football")
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = model,
                contentDescription = "Event Image",
                contentScale = ContentScale.Crop
            )

            Text(text = "Event detail screen $eventId")
        }
    }
}


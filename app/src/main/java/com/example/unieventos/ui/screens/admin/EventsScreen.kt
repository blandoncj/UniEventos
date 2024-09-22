package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.unieventos.models.Event
import com.example.unieventos.ui.components.events.EventCard
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

/**
 * Event list component
 * @param paddingValues: Padding values
 * @param onNavigateToEventDetail: Function to navigate to the event detail screen
 * @param hazeState: Haze state
 */
@Composable
fun EventsScreen(
    paddingValues: PaddingValues,
    onNavigateToEventDetail: (Int) -> Unit,
    hazeState: HazeState
) {
    LazyColumn(
        modifier = Modifier
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface)
            ),
        contentPadding = paddingValues
    ) {
        items(getEventList()) {
            EventCard(
                event = it,
                onClick = { onNavigateToEventDetail(it.id) }
            )
        }
    }
}

private fun getEventList(): List<Event> {
    return listOf(
        Event(1, "Evento 1", "Armenia", "2021-10-10", "https://loremflickr.com/400/400/football"),
        Event(2, "Evento 2", "Pereira", "2021-10-10", "https://loremflickr.com/400/400/football"),
        Event(3, "Evento 3", "Manizales", "2021-10-10", "https://loremflickr.com/400/400/football"),
        Event(4, "Evento 4", "Medellín", "2021-10-10", "https://loremflickr.com/400/400/football"),
    )
}
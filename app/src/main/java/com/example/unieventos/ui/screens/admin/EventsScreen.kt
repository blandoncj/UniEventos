package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.unieventos.models.Event
import com.example.unieventos.ui.components.events.EventCard
import com.example.unieventos.viewmodel.EventsViewModel
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
    eventsViewModel: EventsViewModel,
    paddingValues: PaddingValues,
    onNavigateToEventDetail: (Int) -> Unit,
    hazeState: HazeState
) {

    val events = eventsViewModel.events.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface)
            ),
        contentPadding = paddingValues
    ) {
        items(events) {
            EventCard(
                event = it,
                onClick = { onNavigateToEventDetail(it.id) }
            )
        }
    }

}

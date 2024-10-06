package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unieventos.models.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsViewModel : ViewModel() {

    private val _events = MutableStateFlow(emptyList<Event>())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        _events.value = getEvents()
    }

    private fun getEvents(): List<Event> {
        return listOf(
            Event(
                1,
                "Evento 1",
                "Armenia",
                "Calle 6",
                "Partido de Futbol",
                "2021-10-10",
                "Deportes",
                "https://loremflickr.com/400/400/football?random"
            ),
            Event(
                2,
                "Evento 2",
                "Armenia",
                "Calle 6",
                "Partido de Futbol",
                "2021-10-10",
                "Deportes",
                "https://loremflickr.com/400/400/football?random"
            ),
            Event(
                3,
                "Evento 3",
                "Pereira",
                "Calle 6",
                "Partido de Futbol",
                "2021-10-10",
                "Deportes",
                "https://loremflickr.com/400/400/football?random"
            ),
            Event(
                4,
                "Evento 4",
                "Medellín",
                "Calle 6",
                "Partido de Futbol",
                "2021-10-10",
                "Deportes",
                "https://loremflickr.com/400/400/football?random"
            ),
        )
    }

    fun getEventById(id: Int): Event? {
        return _events.value.find { it.id == id }
    }

    fun searchEvents(query: String): List<Event> {
        return _events.value.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun createEvent(event: Event) {
        _events.value += event
    }

    fun updateEvent(event: Event) {
        val index = _events.value.indexOfFirst { it.id == event.id }
        if (index != -1) {
            _events.value = _events.value.toMutableList().apply {
                set(index, event)
            }
        }
    }

    fun deleteEvent(event: Event) {
        _events.value -= event
    }

}
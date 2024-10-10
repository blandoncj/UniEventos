package com.example.unieventos.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Event
import com.example.unieventos.models.Locality
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class EventsViewModel : ViewModel() {

    private val _events = MutableStateFlow(emptyList<Event>())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        _events.value = getEvents()
    }

    private fun getEvents(): List<Event> {
        return listOf(
            Event(
                id = 1,
                name = "Evento 1",
                city = "Armenia",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                posterImage = "https://loremflickr.com/400/400/football?random",
                localitiesImage = "https://loremflickr.com/400/400/football?random",
                localities = mutableListOf(
                    Locality(0, "Localidad 1", 100, 50.0),
                    Locality(1, "Localidad 2", 200, 100.0)
                )
            ),
            Event(
                id = 2,
                name = "Evento 2",
                city = "Armenia",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                posterImage = "https://loremflickr.com/400/400/football?random",
                localitiesImage = "https://loremflickr.com/400/400/football?random",
                localities = mutableListOf(
                    Locality(0, "Localidad 1", 100, 50.0),
                    Locality(1, "Localidad 2", 200, 100.0)
                )
            ),
            Event(
                id = 3,
                name = "Evento 3",
                city = "Pereira",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                posterImage = "https://loremflickr.com/400/400/football?random",
                localitiesImage = "https://loremflickr.com/400/400/football?random",
                localities = mutableListOf(
                    Locality(0, "Localidad 1", 100, 50.0),
                    Locality(1, "Localidad 2", 200, 100.0)
                )
            ),
            Event(
                id = 4,
                name = "Evento 4",
                city = "Medellín",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                posterImage = "https://loremflickr.com/400/400/football?random",
                localitiesImage = "https://loremflickr.com/400/400/football?random",
                localities = mutableListOf(
                    Locality(0, "Localidad 1", 100, 50.0),
                    Locality(1, "Localidad 2", 200, 100.0)
                )
            )
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

    fun addLocality(event: Event, locality: Locality) {
        val index = _events.value.indexOfFirst { it.name == event.name }
        if (index != -1) {
            _events.value = _events.value.toMutableList().apply {
                get(index).localities.toMutableList().add(locality)
            }
        }
    }

    fun deleteLocality(event: Event, locality: Locality) {
        event.localities.toMutableList().removeIf({ it.id == locality.id })
    }

    @SuppressLint("NewApi")
    fun validateDate(date: String): DateError {
        return try {
            val expirationDate = LocalDate.parse(date)
            if (expirationDate.isBefore(LocalDate.now())) {
                DateError.INVALID
            } else {
                DateError.NONE
            }
        } catch (e: Exception) {
            DateError.NONE
        }
    }

}
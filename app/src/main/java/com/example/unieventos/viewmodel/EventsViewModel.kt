package com.example.unieventos.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Event
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
                imageUrl = "https://loremflickr.com/400/400/football?random",
                locations = mutableListOf(
                    Pair("Localidad 1", Pair("100", "50.0")),
                    Pair("Localidad 2", Pair("200", "100.0"))
                )  // Lista de localidades con aforo y precio
            ),
            Event(
                id = 2,
                name = "Evento 2",
                city = "Armenia",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                imageUrl = "https://loremflickr.com/400/400/football?random",
                locations = mutableListOf()  // Lista vacía si no deseas incluir localidades
            ),
            Event(
                id = 3,
                name = "Evento 3",
                city = "Pereira",
                address = "Calle 6",
                description = "Partido de Futbol",
                date = "2021-10-10",
                category = "Deportes",
                imageUrl = "https://loremflickr.com/400/400/football?random",
                locations = mutableListOf(
                    Pair("Localidad 1", Pair("150", "75.0"))
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
                imageUrl = "https://loremflickr.com/400/400/football?random",
                locations = mutableListOf(
                    Pair("Localidad 1", Pair("200", "120.0")),
                    Pair("Localidad 2", Pair("300", "150.0"))
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
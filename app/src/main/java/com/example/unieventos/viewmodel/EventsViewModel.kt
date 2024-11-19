package com.example.unieventos.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Event
import com.example.unieventos.models.Locality
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class EventsViewModel : ViewModel() {

    val db = Firebase.firestore
    private val _events = MutableStateFlow(emptyList<Event>())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            _events.value = getEvents()
        }
    }

    suspend fun getEvents(): List<Event> {
        val snapshot = db.collection("events")
            .get()
            .await()

        return snapshot.documents.mapNotNull {
            val event = it.toObject(Event::class.java)
            requireNotNull(event)
            event.id = it.id
            event
        }
    }

    suspend fun getEventById(id: String): Event? {
        val snapshot = db.collection("events")
            .document(id)
            .get()
            .await()

        val event = snapshot.toObject(Event::class.java)
        event?.id = snapshot.id
        return event
    }

    fun createEvent(event: Event) {
        viewModelScope.launch {
            db.collection("events")
                .add(event)
                .await()
            loadEvents()
        }
    }

    fun searchEvents(query: String): List<Event> {
        return listOf()
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch {
            db.collection("events")
                .document(event.id)
                .set(event)
                .await()
            loadEvents()
        }
    }

    fun deleteEvent(eventId: String) {
        viewModelScope.launch {
            db.collection("events")
                .document(eventId)
                .delete()
                .await()
            loadEvents()
        }
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

/*
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
*/

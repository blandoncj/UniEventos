package com.example.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.unieventos.ui.navigation.Navigation
import com.example.unieventos.ui.theme.UniEventosTheme
import com.example.unieventos.viewmodel.CartViewModel
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel

/**
 * Main activity of the app, responsible for setting up the navigation and view models.
 * The view models are created using the viewModels() extension function, which is part of the
 */
class MainActivity : ComponentActivity() {

    private val usersViewModel: UsersViewModel by viewModels()
    private val eventsViewModel: EventsViewModel by viewModels()
    private val couponsViewModel: CouponsViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniEventosTheme {
                Navigation(
                    usersViewModel = usersViewModel,
                    eventsViewModel = eventsViewModel,
                    couponsViewModel = couponsViewModel,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}
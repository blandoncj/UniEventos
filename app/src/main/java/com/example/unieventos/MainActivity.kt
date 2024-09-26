package com.example.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unieventos.ui.navigation.Navigation
import com.example.unieventos.ui.theme.UniEventosTheme
import com.example.unieventos.viewmodel.EventsViewModel

class MainActivity : ComponentActivity() {

    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniEventosTheme {
                Navigation(
                    eventsViewModel = eventsViewModel
                )
            }
        }
    }
}

package com.example.unieventos.ui.screens.customer

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.unieventos.ui.components.customer.CustomerBottomBar
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.ui.screens.admin.EventsScreen
import dev.chrisbanes.haze.HazeState

@Composable
fun CustomerHomeScreen(
    onNavigateToEventDetail: (Int) -> Unit,
) {
    val hazeState = remember { HazeState() }

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            MainTopBar(
                when (selectedTab) {
                    0 -> "Eventos"
                    1 -> "Perfil"
                    else -> "Eventos"
                },
                hazeState = hazeState
            )
        },
        bottomBar = {
            CustomerBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> EventsScreen(
                paddingValues = paddingValues,
                hazeState = hazeState,
                onNavigateToEventDetail = onNavigateToEventDetail
            )
            1 -> ProfileScreen(
                paddingValues = paddingValues,
                hazeState = hazeState
            )
        }
    }
}
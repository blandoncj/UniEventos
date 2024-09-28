package com.example.unieventos.ui.screens.customer

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R
import com.example.unieventos.ui.components.customer.CustomerBottomBar
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.ui.screens.admin.EventsScreen
import com.example.unieventos.viewmodel.EventsViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun CustomerHomeScreen(
    eventsViewModel: EventsViewModel,
    onNavigateToEventDetail: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val hazeState = remember { HazeState() }

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            MainTopBar(
                when (selectedTab) {
                    0 -> stringResource(id = R.string.events_lbl)
                    1 -> stringResource(id = R.string.profile_lbl)
                    else -> stringResource(id = R.string.events_lbl)
                },
                hazeState = hazeState,
                onLogout = onLogout
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
                getEventList = { eventsViewModel.events.value },
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
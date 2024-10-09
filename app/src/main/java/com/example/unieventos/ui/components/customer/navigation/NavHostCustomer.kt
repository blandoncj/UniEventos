package com.example.unieventos.ui.components.customer.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.unieventos.ui.screens.admin.EventsScreen
import com.example.unieventos.ui.screens.customer.ProfileScreen
import com.example.unieventos.viewmodel.EventsViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun NavHostCustomer(
    navController: NavHostController,
    paddingValues: PaddingValues,
    eventsViewModel: EventsViewModel,
    onNavigateToEventDetail: (Int) -> Unit,
    hazeState: HazeState
) {

    NavHost(
        modifier = Modifier.fillMaxWidth(),
        navController = navController,
        startDestination = ItemTabCustomer.EventsTab
    ) {
        composable<ItemTabCustomer.EventsTab> {
            EventsScreen(
                eventsViewModel = eventsViewModel,
                paddingValues = paddingValues,
                onNavigateToEventDetail = onNavigateToEventDetail,
                hazeState = hazeState
            )
        }

        composable<ItemTabCustomer.ProfileTab> {
            ProfileScreen(
                paddingValues = paddingValues,
                hazeState = hazeState
            )
        }
    }

}
package com.example.unieventos.ui.components.admin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.unieventos.ui.screens.CouponsScreen
import com.example.unieventos.ui.screens.EventsScreen
import com.example.unieventos.ui.screens.admin.AdminProfileScreen
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun NavHostAdmin(
    navController: NavHostController,
    paddingValues: PaddingValues,
    eventsViewModel: EventsViewModel,
    couponsViewModel: CouponsViewModel,
    usersViewModel: UsersViewModel,
    userId: Int,
    onNavigateToEventDetail: (Int) -> Unit,
    onNavigateToCouponDetail: (Int) -> Unit,
    hazeState: HazeState
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ItemTabAdmin.EventsTab
    ) {
        composable<ItemTabAdmin.EventsTab> {
            EventsScreen(
                eventsViewModel = eventsViewModel,
                paddingValues = paddingValues,
                onNavigateToEventDetail = onNavigateToEventDetail,
                hazeState = hazeState
            )
        }

        composable<ItemTabAdmin.CouponsTab> {
            CouponsScreen(
                couponsViewModel = couponsViewModel,
                paddingValues = paddingValues,
                onNavigateToCouponDetail = onNavigateToCouponDetail,
                hazeState = hazeState
            )
        }

        composable<ItemTabAdmin.ProfileTab> {
            AdminProfileScreen(
                usersViewModel = usersViewModel,
                userId = userId,
                hazeState = hazeState,
                paddingValues = paddingValues
            )
        }

    }
}
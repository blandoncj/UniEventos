package com.example.unieventos.ui.components.customer.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.unieventos.ui.screens.CouponsScreen
import com.example.unieventos.ui.screens.EventsScreen
import com.example.unieventos.ui.screens.customer.CartScreen
import com.example.unieventos.ui.screens.customer.CustomerProfileScreen
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun NavHostCustomer(
    navController: NavHostController,
    paddingValues: PaddingValues,
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    couponsViewModel: CouponsViewModel,
    userId: Int,
    onNavigateToEventDetail: (Int) -> Unit,
    hazeState: HazeState,
    onLogout: () -> Unit
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

        composable<ItemTabCustomer.CouponsTab> {
            CouponsScreen(
                couponsViewModel = couponsViewModel,
                paddingValues = paddingValues,
                onNavigateToCouponDetail = {},
                hazeState = hazeState
            )
        }

        composable<ItemTabCustomer.CartTab> {
            CartScreen()
        }

        composable<ItemTabCustomer.ProfileTab> {
            CustomerProfileScreen(
                paddingValues = paddingValues,
                hazeState = hazeState,
                usersViewModel = usersViewModel,
                userId = userId,
                onLogout = onLogout
            )
        }
    }

}
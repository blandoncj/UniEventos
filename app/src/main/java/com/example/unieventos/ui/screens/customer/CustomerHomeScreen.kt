package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.unieventos.R
import com.example.unieventos.ui.components.customer.CustomerBottomBar
import com.example.unieventos.ui.components.customer.navigation.NavHostCustomer
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.viewmodel.CartViewModel
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun CustomerHomeScreen(
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    couponsViewModel: CouponsViewModel,
    cartViewModel: CartViewModel,
    userId: Int,
    onNavigateToEventDetail: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val hazeState = remember { HazeState() }
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            MainTopBar(
                when (selectedTab) {
                    0 -> stringResource(id = R.string.events_lbl)
                    1 -> stringResource(id = R.string.coupons_lbl)
                    2 -> stringResource(id = R.string.cart_lbl)
                    3 -> stringResource(id = R.string.profile_lbl)
                    else -> stringResource(id = R.string.events_lbl)
                },
                hazeState = hazeState,
                onLogout = onLogout
            )
        },
        bottomBar = {
            CustomerBottomBar(
                navController = navController,
                onTabSelected = { selectedTab = it },
                cartViewModel = cartViewModel
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        NavHostCustomer(
            navController = navController,
            paddingValues = paddingValues,
            eventsViewModel = eventsViewModel,
            usersViewModel = usersViewModel,
            couponsViewModel = couponsViewModel,
            userId = userId,
            onNavigateToEventDetail = onNavigateToEventDetail,
            hazeState = hazeState,
            onLogout = onLogout,
            cartViewModel = cartViewModel
        )
    }
}
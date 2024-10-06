package com.example.unieventos.ui.screens.admin

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.unieventos.R
import com.example.unieventos.ui.components.admin.AdminBottomBar
import com.example.unieventos.ui.components.admin.navigation.NavHostAdmin
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.ui.components.utils.FloatingAddButton
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun HomeAdminScreen(
    eventsViewModel: EventsViewModel,
    couponsViewModel: CouponsViewModel,
    usersViewModel: UsersViewModel,
    userId: Int,
    onNavigateToEventDetail: (Int) -> Unit,
    onNavigateToCreateEvent: () -> Unit,
    onNavigateToCreateCoupon: () -> Unit,
    onNavigateToCouponDetail: (Int) -> Unit,
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
                    else -> stringResource(id = R.string.events_lbl)
                },
                hazeState = hazeState,
                onLogout = onLogout
            )
        },
        floatingActionButton = {
            FloatingAddButton(onClick = {
                when (selectedTab) {
                    0 -> onNavigateToCreateEvent()
                    1 -> onNavigateToCreateCoupon()
                }
            })
        },
        bottomBar = {
            AdminBottomBar(
                navController = navController,
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        NavHostAdmin(
            navController = navController,
            paddingValues = paddingValues,
            couponsViewModel = couponsViewModel,
            eventsViewModel = eventsViewModel,
            onNavigateToEventDetail = onNavigateToEventDetail,
            onNavigateToCouponDetail = onNavigateToCouponDetail,
            hazeState = hazeState,
            usersViewModel = usersViewModel,
            userId = userId,
        )

    }
}
package com.example.unieventos.ui.screens.admin

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R
import com.example.unieventos.ui.components.admin.AdminBottomBar
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.ui.components.utils.FloatingAddButton
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import dev.chrisbanes.haze.HazeState

/**
 * Home screen for admin users
 * @param onNavigateToEventDetail: Function to navigate to the event detail screen
 */
@Composable
fun HomeAdminScreen(
    eventsViewModel: EventsViewModel,
    couponsViewModel: CouponsViewModel,
    onNavigateToEventDetail: (Int) -> Unit,
    onNavigateToCreateEvent: () -> Unit,
    onNavigateToCreateCoupon: () -> Unit,
    onNavigateToCouponDetail: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val hazeState = remember { HazeState() }

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
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> EventsScreen(
                getEventList = { eventsViewModel.events.value },
                paddingValues = paddingValues,
                onNavigateToEventDetail = onNavigateToEventDetail,
                hazeState = hazeState
            )

            1 -> CouponsScreen(
                getCouponList = { couponsViewModel.coupons.value },
                paddingValues = paddingValues,
                onNavigateToCouponDetail = onNavigateToCouponDetail,
                hazeState = hazeState
            )
        }
    }
}
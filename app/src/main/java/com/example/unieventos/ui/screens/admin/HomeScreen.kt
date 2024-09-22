package com.example.unieventos.ui.screens.admin

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.unieventos.ui.components.admin.AdminBottomBar
import com.example.unieventos.ui.components.utils.MainTopBar
import com.example.unieventos.ui.components.utils.FloatingAddButton
import dev.chrisbanes.haze.HazeState

/**
 * Home screen for admin users
 * @param onNavigateToEventDetail: Function to navigate to the event detail screen
 */
@Composable
fun HomeAdminScreen(
    onNavigateToEventDetail: (Int) -> Unit,
    onNavigateToCreateEvent: () -> Unit,
    onNavigateToCreateCoupon: () -> Unit,
    onNavigateToCouponDetail: (Int) -> Unit
) {
    val hazeState = remember { HazeState() }

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            MainTopBar(
                when (selectedTab) {
                    0 -> "Eventos"
                    1 -> "Cupones"
                    else -> "Eventos"
                },
                hazeState = hazeState
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
                paddingValues = paddingValues,
                onNavigateToEventDetail = onNavigateToEventDetail,
                hazeState = hazeState
            )

            1 -> CouponsScreen(
                paddingValues = paddingValues,
                onNavigateToCouponDetail = onNavigateToCouponDetail,
                hazeState = hazeState
            )
        }
    }
}
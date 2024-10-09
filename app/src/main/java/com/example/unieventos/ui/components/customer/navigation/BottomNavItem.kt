package com.example.unieventos.ui.components.customer.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: ItemTabCustomer,
    val label: Int,
    val icon: ImageVector
) {

    data object Events : BottomNavItem(
        ItemTabCustomer.EventsTab,
        com.example.unieventos.R.string.events_lbl,
        com.example.unieventos.ui.components.admin.navigation.BottomNavItem.Events.icon
    )

    data object Profile : BottomNavItem(
        ItemTabCustomer.ProfileTab,
        com.example.unieventos.R.string.profile_lbl,
        com.example.unieventos.ui.components.admin.navigation.BottomNavItem.Profile.icon
    )

}
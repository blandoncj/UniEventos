package com.example.unieventos.ui.components.admin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.unieventos.R

/**
 * Admin bottom navigation items.
 * @param route The route of the item.
 * @param label The label of the item.
 * @param icon The icon of the item.
 */
sealed class BottomNavItem(
    val route: ItemTabAdmin,
    val label: Int,
    val icon: ImageVector
) {
    
    data object Events : BottomNavItem(
        ItemTabAdmin.EventsTab,
        R.string.events_lbl,
        Icons.Default.Stadium
    )

    data object Coupons : BottomNavItem(
        ItemTabAdmin.CouponsTab,
        R.string.coupons_lbl,
        Icons.Default.Discount
    )

    data object Profile : BottomNavItem(
        ItemTabAdmin.ProfileTab,
        R.string.profile_lbl,
        Icons.Default.Person
    )

}
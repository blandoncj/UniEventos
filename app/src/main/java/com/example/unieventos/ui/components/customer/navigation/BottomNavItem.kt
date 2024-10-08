package com.example.unieventos.ui.components.customer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.unieventos.R

sealed class BottomNavItem(
    val route: ItemTabCustomer,
    val label: Int,
    val icon: ImageVector
) {

    data object Events : BottomNavItem(
        ItemTabCustomer.EventsTab,
        R.string.events_lbl,
        Icons.Default.Stadium
    )

    data object Coupons : BottomNavItem(
        ItemTabCustomer.CouponsTab,
        R.string.coupons_lbl,
        Icons.Default.Discount
    )

    data object Cart : BottomNavItem(
        ItemTabCustomer.CartTab,
        R.string.cart_lbl,
        Icons.Default.ShoppingCart
    )

    data object Profile : BottomNavItem(
        ItemTabCustomer.ProfileTab,
        R.string.profile_lbl,
        Icons.Default.Person
    )

}
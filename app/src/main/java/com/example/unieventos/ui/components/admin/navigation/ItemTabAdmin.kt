package com.example.unieventos.ui.components.admin.navigation

import kotlinx.serialization.Serializable

/**
 * Sealed class that represents the tabs that are displayed in the admin navigation bar.
 */
sealed class ItemTabAdmin {

    @Serializable
    data object EventsTab : ItemTabAdmin()

    @Serializable
    data object CouponsTab : ItemTabAdmin()

    @Serializable
    data object ProfileTab : ItemTabAdmin()

}
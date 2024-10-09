package com.example.unieventos.ui.components.customer.navigation

import kotlinx.serialization.Serializable

sealed class ItemTabCustomer {

    @Serializable
    data object EventsTab : ItemTabCustomer()

    @Serializable
    data object CouponsTab : ItemTabCustomer()

    @Serializable
    data object ProfileTab : ItemTabCustomer()

}
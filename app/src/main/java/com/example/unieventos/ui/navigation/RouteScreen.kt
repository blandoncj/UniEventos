package com.example.unieventos.ui.navigation

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object Login: RouteScreen()

    @Serializable
    data object Signup: RouteScreen()

    @Serializable
    data object ConfirmAccount: RouteScreen()

    @Serializable
    data object  RecoverPassword: RouteScreen()

    @Serializable
    data object  ChangePassword: RouteScreen()

}
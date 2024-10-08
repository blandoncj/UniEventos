package com.example.unieventos.ui.navigation

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object Login : RouteScreen()

    @Serializable
    data object Signup : RouteScreen()

    @Serializable
    data object ConfirmAccount : RouteScreen()

    @Serializable
    data object RecoverPassword : RouteScreen()

    @Serializable
    data object ChangePassword : RouteScreen()

    @Serializable
    data object AdminHome : RouteScreen()

    @Serializable
    data object CreateEvent : RouteScreen()

    @Serializable
    data class AdminEventDetail(val eventId: Int) : RouteScreen()

    @Serializable
    data object Coupons : RouteScreen()

    @Serializable
    data object CreateCoupon : RouteScreen()

    @Serializable
    data class CouponDetail(val couponId: Int) : RouteScreen()

    @Serializable
    data object CustomerHome : RouteScreen()

    @Serializable
    data class CustomerEventDetail(val eventId: Int) : RouteScreen()

}
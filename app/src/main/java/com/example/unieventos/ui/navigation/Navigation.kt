package com.example.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.unieventos.ui.screens.LoginScreen
import com.example.unieventos.ui.screens.admin.CouponDetailScreen
import com.example.unieventos.ui.screens.admin.CreateCouponScreen
import com.example.unieventos.ui.screens.admin.CreateEventScreen
import com.example.unieventos.ui.screens.admin.EventDetailScreen
import com.example.unieventos.ui.screens.admin.HomeAdminScreen
import com.example.unieventos.ui.screens.customer.ChangePasswordScreen
import com.example.unieventos.ui.screens.customer.ConfirmAccountScreen
import com.example.unieventos.ui.screens.customer.CustomerHomeScreen
import com.example.unieventos.ui.screens.customer.RecoverPasswordScreen
import com.example.unieventos.ui.screens.customer.SignupScreen

/**
 * Navigation composable that handles the navigation between screens.
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToSignup = { navController.navigate(RouteScreen.Signup) },
                onNavigateToRecoverPassword = { navController.navigate(RouteScreen.RecoverPassword) },
                onNavigateToAdminHome = {
                    navController.navigate(RouteScreen.AdminHome) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToCustomerHome = {
                    navController.navigate(RouteScreen.CustomerHome)
                }
            )
        }

        composable<RouteScreen.Signup> {
            SignupScreen(
                onBack = { navController.navigate(RouteScreen.Login) },
                onNavigateToConfirmAccount = { navController.navigate(RouteScreen.ConfirmAccount) }
            )
        }

        composable<RouteScreen.ConfirmAccount> {
            ConfirmAccountScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.RecoverPassword> {
            RecoverPasswordScreen(
                onBack = { navController.navigate(RouteScreen.Login) },
                onNavigateToChangePassword = { navController.navigate(RouteScreen.ChangePassword) }
            )
        }

        composable<RouteScreen.ChangePassword> {
            ChangePasswordScreen(
                onBack = { navController.navigate(RouteScreen.RecoverPassword) }
            )
        }

        composable<RouteScreen.AdminHome> {
            HomeAdminScreen(
                onNavigateToCreateEvent = { navController.navigate(RouteScreen.CreateEvent) },
                onNavigateToEventDetail = { eventId ->
                    navController.navigate(
                        RouteScreen.EventDetail(
                            eventId
                        )
                    )
                },
                onNavigateToCreateCoupon = { navController.navigate(RouteScreen.CreateCoupon) },
                onNavigateToCouponDetail = { couponId ->
                    navController.navigate(RouteScreen.CouponDetail(couponId))
                }
            )
        }

        composable<RouteScreen.CreateEvent> {
            CreateEventScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.EventDetail> {
            val eventId = it.toRoute<RouteScreen.EventDetail>()
            EventDetailScreen(
                eventId = eventId.eventId,
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.CreateCoupon> {
            CreateCouponScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.CouponDetail> {
            val couponId = it.toRoute<RouteScreen.CouponDetail>()
            CouponDetailScreen(
                couponId = couponId.couponId,
                onBack = { navController.popBackStack() }
            )
        }

        composable<RouteScreen.CustomerHome> {
            CustomerHomeScreen(
                onNavigateToEventDetail = { eventId ->
                    navController.navigate(RouteScreen.EventDetail(eventId))
                }
            )
        }
    }
}
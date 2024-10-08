package com.example.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.unieventos.enums.Role
import com.example.unieventos.ui.screens.LoginScreen
import com.example.unieventos.ui.screens.admin.CouponDetailScreen
import com.example.unieventos.ui.screens.admin.CreateCouponScreen
import com.example.unieventos.ui.screens.admin.CreateEventScreen
import com.example.unieventos.ui.screens.admin.AdminEventDetailScreen
import com.example.unieventos.ui.screens.admin.AdminHomeScreen
import com.example.unieventos.ui.screens.ChangePasswordScreen
import com.example.unieventos.ui.screens.customer.ConfirmAccountScreen
import com.example.unieventos.ui.screens.customer.CustomerEventDetailScreen
import com.example.unieventos.ui.screens.customer.CustomerHomeScreen
import com.example.unieventos.ui.screens.RecoverPasswordScreen
import com.example.unieventos.ui.screens.customer.CartScreen
import com.example.unieventos.ui.screens.customer.SignupScreen
import com.example.unieventos.utils.SharedPreferencesUtils
import com.example.unieventos.viewmodel.CartViewModel
import com.example.unieventos.viewmodel.CouponsViewModel
import com.example.unieventos.viewmodel.EventsViewModel
import com.example.unieventos.viewmodel.UsersViewModel


@Composable
fun Navigation(
    usersViewModel: UsersViewModel,
    eventsViewModel: EventsViewModel,
    couponsViewModel: CouponsViewModel,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current
    val navController = rememberNavController()

    var startDestination: RouteScreen = RouteScreen.Login
    val session = SharedPreferencesUtils.getCurrentUser(context)

    if (session != null) {
        startDestination = when (session.role) {
            Role.ADMIN -> RouteScreen.AdminHome
            Role.CUSTOMER -> RouteScreen.CustomerHome
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<RouteScreen.Login> {
            LoginScreen(
                usersViewModel = usersViewModel,
                onNavigateToSignup = { navController.navigate(RouteScreen.Signup) },
                onNavigateToRecoverPassword = { navController.navigate(RouteScreen.RecoverPassword) },
                onNavigateToHome = { role ->
                    val home = if (role == Role.ADMIN) {
                        RouteScreen.AdminHome
                    } else {
                        RouteScreen.CustomerHome
                    }
                    navController.navigate(home) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RouteScreen.Signup> {
            SignupScreen(
                usersViewModel = usersViewModel,
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
            AdminHomeScreen(
                eventsViewModel = eventsViewModel,
                couponsViewModel = couponsViewModel,
                usersViewModel = usersViewModel,
                userId = session?.id ?: 0,
                onNavigateToCreateEvent = { navController.navigate(RouteScreen.CreateEvent) },
                onNavigateToEventDetail = { eventId ->
                    navController.navigate(
                        RouteScreen.AdminEventDetail(
                            eventId
                        )
                    )
                },
                onNavigateToCreateCoupon = { navController.navigate(RouteScreen.CreateCoupon) },
                onNavigateToCouponDetail = { couponId ->
                    navController.navigate(RouteScreen.CouponDetail(couponId))
                },
                onLogout = {
                    SharedPreferencesUtils.clearPreferences(context)
                    navController.navigate(RouteScreen.Login) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<RouteScreen.CreateEvent> {
            CreateEventScreen(
                onBack = { navController.popBackStack() },
                eventsViewModel = eventsViewModel
            )
        }

        composable<RouteScreen.AdminEventDetail> {
            val eventId = it.toRoute<RouteScreen.AdminEventDetail>()
            AdminEventDetailScreen(
                eventsViewModel = eventsViewModel,
                eventId = eventId.eventId,
                onBack = { navController.navigate(RouteScreen.AdminHome) }
            )
        }

        composable<RouteScreen.CreateCoupon> {
            CreateCouponScreen(
                couponsViewModel = couponsViewModel,
                onBack = { navController.navigate(RouteScreen.AdminHome) }
            )
        }

        composable<RouteScreen.CouponDetail> {
            val couponId = it.toRoute<RouteScreen.CouponDetail>()
            CouponDetailScreen(
                couponsViewModel = couponsViewModel,
                couponId = couponId.couponId,
                onBack = { navController.navigate(RouteScreen.AdminHome) }
            )
        }

        composable<RouteScreen.CustomerHome> {
            CustomerHomeScreen(
                eventsViewModel = eventsViewModel,
                usersViewModel = usersViewModel,
                couponsViewModel = couponsViewModel,
                userId = session?.id ?: 0,
                onNavigateToEventDetail = { eventId ->
                    navController.navigate(RouteScreen.CustomerEventDetail(eventId))
                },
                onLogout = {
                    SharedPreferencesUtils.clearPreferences(context)
                    navController.navigate(RouteScreen.Login) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RouteScreen.CustomerEventDetail> {
            val eventId = it.toRoute<RouteScreen.AdminEventDetail>()
            CustomerEventDetailScreen(
                eventsViewModel = eventsViewModel,
                eventId = eventId.eventId,
                onBack = { navController.navigate(RouteScreen.CustomerHome) }
            )
        }

        composable<RouteScreen.Cart> {
            CartScreen(
            )
        }
    }
}
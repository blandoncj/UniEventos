package com.example.unieventos.ui.components.customer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.unieventos.R
import com.example.unieventos.ui.components.customer.navigation.BottomNavItem
import com.example.unieventos.ui.components.customer.navigation.ItemTabCustomer
import com.example.unieventos.viewmodel.CartViewModel

@Composable
fun CustomerBottomBar(
    navController: NavHostController,
    onTabSelected: (Int) -> Unit,
    cartViewModel: CartViewModel
) {

    val tabs = listOf(
        BottomNavItem.Events,
        BottomNavItem.Coupons,
        BottomNavItem.Cart,
        BottomNavItem.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {

                    if (tab.route == ItemTabCustomer.CartTab) {
                        BadgedBox(
                            badge = {
                                if (cartViewModel.getCartSize() > 0) {
                                    Badge {
                                        Text(cartViewModel.getCartSize().toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        }
                    } else {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = null
                        )
                    }

                },
                label = { Text(stringResource(id = tab.label)) },
                selected = navBackStackEntry?.destination?.hasRoute(tab.route::class)
                    ?: false,
                onClick = {
                    onTabSelected(index)
                    navController.navigate(tab.route)
                }
            )
        }
    }
}
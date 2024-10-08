package com.example.unieventos.ui.components.customer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stadium
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

@Composable
fun CustomerBottomBar(
    navController: NavHostController,
    onTabSelected: (Int) -> Unit
) {

    val tabs = listOf(
        BottomNavItem.Events,
        BottomNavItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = null
                    )
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
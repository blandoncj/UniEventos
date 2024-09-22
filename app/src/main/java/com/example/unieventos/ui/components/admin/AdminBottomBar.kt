package com.example.unieventos.ui.components.admin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R

/**
 * Admin bottom bar composable that displays the bottom bar for the admin screens.
 * @param selectedTab The selected tab index.
 * @param onTabSelected The callback to handle the tab selection.
 */
@Composable
fun AdminBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Stadium,
                    contentDescription = "Events icon"
                )
            },
            label = { Text(text = stringResource(id = R.string.events_lbl)) },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Discount,
                    contentDescription = "Coupons icon"
                )
            },
            label = { Text(text = stringResource(id = R.string.coupons_lbl)) },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
    }
}
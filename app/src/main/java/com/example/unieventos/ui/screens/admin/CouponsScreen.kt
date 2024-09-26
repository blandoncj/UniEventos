package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.unieventos.models.Coupon
import com.example.unieventos.ui.components.coupons.CouponCard
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

/**
 * Coupon list component
 * @param paddingValues: Padding values
 * @param onNavigateToCouponDetail: Function to navigate to the coupon detail screen
 * @param hazeState: Haze state
 */
@Composable
fun CouponsScreen(
    getCouponList: () -> List<Coupon>,
    paddingValues: PaddingValues,
    onNavigateToCouponDetail: (Int) -> Unit,
    hazeState: HazeState
) {
    LazyColumn(
        modifier = Modifier
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface)
            ),
        contentPadding = paddingValues
    ) {
        items(getCouponList()) {
            CouponCard(
                coupon = it,
                onClick = { onNavigateToCouponDetail(it.id) }
            )
        }
    }
}

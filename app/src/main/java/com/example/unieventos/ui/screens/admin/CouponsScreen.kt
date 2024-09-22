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

private fun getCouponList(): List<Coupon> {
    return listOf(
        Coupon(
            1,
            "Cupón 1",
            "2A1083AFK",
            10,
            "https://loremflickr.com/400/400/coupon",
            "05-09-2024"
        ),
        Coupon(
            2,
            "Cupón 2",
            "2A1083AFK",
            10,
            "https://loremflickr.com/400/400/football",
            "05-09-2024"
        ),
        Coupon(
            3,
            "Cupón 3",
            "2A1083AFK",
            10,
            "https://loremflickr.com/400/400/football",
            "05-09-2024"
        ),
        Coupon(
            4,
            "Cupón 4",
            "2A1083AFK",
            10,
            "https://loremflickr.com/400/400/football",
            "05-09-2024"
        ),
    )
}
package com.example.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unieventos.models.Coupon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CouponsViewModel : ViewModel() {

    private val _coupons = MutableStateFlow(emptyList<Coupon>())
    val coupons: StateFlow<List<Coupon>> = _coupons.asStateFlow()

    init {
        _coupons.value = getCoupons()
    }

    fun getCouponById(id: Int): Coupon? {
        return _coupons.value.find { it.id == id }
    }

    fun createCoupon(coupon: Coupon) {
        _coupons.value += coupon
    }

    fun updateCoupon(coupon: Coupon) {
        val index = _coupons.value.indexOfFirst { it.id == coupon.id }
        if (index != -1) {
            _coupons.value = _coupons.value.toMutableList().apply {
                set(index, coupon)
            }
        }
    }

    fun deleteCoupon(coupon: Coupon) {
        _coupons.value -= coupon
    }

    private fun getCoupons(): List<Coupon> {
        return listOf(
            Coupon(
                1,
                "Cupón 1",
                "2A1083AFK",
                10,
                "05-09-2024"
            ),
            Coupon(
                2,
                "Cupón 2",
                "2A1083AFK",
                10,
                "05-09-2024"
            ),
            Coupon(
                3,
                "Cupón 3",
                "2A1083AFK",
                10,
                "05-09-2024"
            ),
            Coupon(
                4,
                "Cupón 4",
                "2A1083AFK",
                10,
                "05-09-2024"
            ),
        )
    }
}
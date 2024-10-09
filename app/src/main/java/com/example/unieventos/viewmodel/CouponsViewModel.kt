package com.example.unieventos.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.unieventos.enums.CouponCodeError
import com.example.unieventos.enums.CouponNameError
import com.example.unieventos.enums.DateError
import com.example.unieventos.enums.NameError
import com.example.unieventos.models.Coupon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class CouponsViewModel : ViewModel() {

    private val _coupons = MutableStateFlow(emptyList<Coupon>())
    val coupons: StateFlow<List<Coupon>> = _coupons.asStateFlow()

    init {
        _coupons.value = getCoupons()
    }

    fun getCouponById(id: Int): Coupon? {
        return _coupons.value.find { it.id == id }
    }

    fun getCouponByCode(code: String): Coupon? {
        return _coupons.value.find { it.code == code }
    }

    fun getCouponByName(name: String): Coupon? {
        return _coupons.value.find { it.name == name }
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

    fun validateName(name: String): CouponNameError {
        return when {
            name.isEmpty() -> CouponNameError.EMPTY
            name.length < 3 -> CouponNameError.INVALID_LENGTH
            name == getCouponByName(name)?.name -> CouponNameError.ALREADY_EXISTS
            else -> CouponNameError.NONE
        }
    }

    fun validateCode(code: String): CouponCodeError {
        return when {
            code.isEmpty() -> CouponCodeError.EMPTY
            code.length < 6 -> CouponCodeError.INVALID_LENGTH
            code == getCouponByCode(code)?.code -> CouponCodeError.ALREADY_EXISTS
            else -> CouponCodeError.NONE
        }
    }



    @SuppressLint("NewApi")
    fun validateDate(date: String): DateError {
        return try {
            val expirationDate = LocalDate.parse(date)
            if (expirationDate.isBefore(LocalDate.now())) {
                DateError.INVALID
            } else {
                DateError.NONE
            }
        } catch (e: Exception) {
            DateError.NONE
        }
    }

}
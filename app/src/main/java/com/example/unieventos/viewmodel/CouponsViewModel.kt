package com.example.unieventos.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unieventos.enums.CouponCodeError
import com.example.unieventos.enums.CouponNameError
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Coupon
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class CouponsViewModel : ViewModel() {
    val db = Firebase.firestore
    private val _coupons = MutableStateFlow(emptyList<Coupon>())
    val coupons: StateFlow<List<Coupon>> = _coupons.asStateFlow()

    init {
        loadCoupons()
    }

    private fun loadCoupons() {
        viewModelScope.launch {
            _coupons.value = getCoupons()
        }
    }

    suspend fun getCoupons(): List<Coupon> {
        val snapshot =
            db
                .collection("coupons")
                .get()
                .await()

        return snapshot.documents.mapNotNull {
            val coupon = it.toObject(Coupon::class.java)
            requireNotNull(coupon)
            coupon.id = it.id
            coupon
        }
    }

    suspend fun getCouponById(id: String): Coupon? {
        val snapshot =
            db
                .collection("coupons")
                .document(id)
                .get()
                .await()

        val coupon = snapshot.toObject(Coupon::class.java)
        coupon?.id = snapshot.id
        return coupon
    }

    fun createCoupon(coupon: Coupon) {
        viewModelScope.launch {
            db
                .collection("coupons")
                .add(coupon)
                .await()
            loadCoupons()
        }
    }

    fun updateCoupon(coupon: Coupon) {
        viewModelScope.launch {
            db
                .collection("coupons")
                .document(coupon.id)
                .set(coupon)
                .await()
            loadCoupons()
        }
    }

    fun deleteCoupon(id: String) {
        viewModelScope.launch {
            db
                .collection("coupons")
                .document(id)
                .delete()
                .await()
            loadCoupons()
        }
    }

    fun getCouponByCode(code: String): Coupon? = _coupons.value.find { it.code == code }

    fun getCouponByName(name: String): Coupon? = _coupons.value.find { it.name == name }

    fun validateName(name: String): CouponNameError =
        when {
            name.isEmpty() -> CouponNameError.EMPTY
            name.length < 3 -> CouponNameError.INVALID_LENGTH
            name == getCouponByName(name)?.name -> CouponNameError.ALREADY_EXISTS
            else -> CouponNameError.NONE
        }

    fun validateCode(code: String): CouponCodeError =
        when {
            code.isEmpty() -> CouponCodeError.EMPTY
            code.length < 6 -> CouponCodeError.INVALID_LENGTH
            code == getCouponByCode(code)?.code -> CouponCodeError.ALREADY_EXISTS
            else -> CouponCodeError.NONE
        }

    @SuppressLint("NewApi")
    fun validateDate(date: String): DateError =
        try {
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


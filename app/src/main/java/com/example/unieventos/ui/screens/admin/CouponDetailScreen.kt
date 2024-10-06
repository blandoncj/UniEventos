package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unieventos.R
import com.example.unieventos.enums.CouponCodeError
import com.example.unieventos.enums.CouponNameError
import com.example.unieventos.ui.components.coupons.CouponForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.viewmodel.CouponsViewModel

@Composable
fun CouponDetailScreen(
    couponsViewModel: CouponsViewModel,
    couponId: Int,
    onBack: () -> Unit
) {
    val coupon = couponsViewModel.getCouponById(couponId)

    if (coupon == null) {
        return
    }

    var name by rememberSaveable { mutableStateOf(coupon.name) }
    var nameError by rememberSaveable { mutableStateOf(CouponNameError.NONE) }
    var code by rememberSaveable { mutableStateOf(coupon.code) }
    var codeError by rememberSaveable { mutableStateOf(CouponCodeError.NONE) }
    var discount by rememberSaveable { mutableStateOf(coupon.discount.toString()) }
    var expandedDiscount by rememberSaveable { mutableStateOf(false) }
    var expirationDate by rememberSaveable { mutableStateOf(coupon.expirationDate) }
    var isDatePicked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = coupon.name, onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            CouponForm(
                name = name,
                onNameChange = { name = it },
                nameError = nameError,
                code = code,
                onCodeChange = { code = it },
                codeError = codeError,
                discount = discount,
                expandedDiscount = expandedDiscount,
                onExpandedChange = { expandedDiscount = it },
                onDiscountSelected = { discount = it },
                expirationDate = expirationDate,
                onExpirationDateChange = { expirationDate = it },
                isDatePicked = isDatePicked,
                onDatePickedChange = { isDatePicked = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                PrimaryButton(
                    text = stringResource(id = R.string.update_coupon_btn),
                    enabled = nameError == CouponNameError.NONE &&
                            codeError == CouponCodeError.NONE &&
                            discount.isNotEmpty() &&
                            expirationDate.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val updatedCoupon = coupon.copy(
                            name = name,
                            code = code,
                            discount = discount.toInt(),
                            expirationDate = expirationDate
                        )
                        couponsViewModel.updateCoupon(updatedCoupon)
                        onBack()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SecondaryButton(
                    text = stringResource(id = R.string.delete_coupon_btn),
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        couponsViewModel.deleteCoupon(coupon)
                        onBack()
                    }
                )
            }
        }
    }
}
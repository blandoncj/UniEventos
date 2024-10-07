package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.CouponCodeError
import com.example.unieventos.enums.CouponNameError
import com.example.unieventos.enums.DateError
import com.example.unieventos.models.Coupon
import com.example.unieventos.ui.components.coupons.CouponForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.viewmodel.CouponsViewModel

/**
 * Create coupon screen composable function.
 * This composable function displays the create coupon screen.
 * @param onBack The function that is called when the user presses the back button.
 */
@Composable
fun CreateCouponScreen(
    couponsViewModel: CouponsViewModel,
    onBack: () -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf(CouponNameError.NONE) }
    var code by rememberSaveable { mutableStateOf("") }
    var codeError by rememberSaveable { mutableStateOf(CouponCodeError.NONE) }
    var discount by rememberSaveable { mutableStateOf("") }
    var expandedDiscount by rememberSaveable { mutableStateOf(false) }
    var expirationDate by rememberSaveable { mutableStateOf("") }
    var isDatePicked by rememberSaveable { mutableStateOf(false) }
    var dateError by rememberSaveable { mutableStateOf(DateError.NONE) }

    fun clearFields() {
        name = ""
        code = ""
        discount = ""
        expirationDate = ""
        isDatePicked = false
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.create_cpn_lbl),
                onBack = onBack
            )
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
                onNameChange = {
                    name = it
                    nameError = couponsViewModel.validateName(name)
                },
                nameError = nameError,
                code = code,
                onCodeChange = {
                    code = it
                    codeError = couponsViewModel.validateCode(code)
                },
                codeError = codeError,
                discount = discount,
                expandedDiscount = expandedDiscount,
                onExpandedChange = { expandedDiscount = it },
                onDiscountSelected = { discount = it },
                expirationDate = expirationDate,
                onExpirationDateChange = { expirationDate = it },
                isDatePicked = isDatePicked,
                onDatePickedChange = {
                    isDatePicked = it
                    dateError = couponsViewModel.validateDate(expirationDate)
                },
                dateError = dateError
            )

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                text = stringResource(id = R.string.create_coupon_btn),
                enabled = nameError == CouponNameError.NONE &&
                        codeError == CouponCodeError.NONE &&
                        dateError == DateError.NONE &&
                        discount.isNotEmpty() &&
                        expirationDate.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    val coupon = Coupon(
                        id = 0,
                        name = name,
                        code = code,
                        discount = discount.toInt(),
                        expirationDate = expirationDate
                    )

                    couponsViewModel.createCoupon(coupon)
                    clearFields()
                }
            )
        }
    }
}
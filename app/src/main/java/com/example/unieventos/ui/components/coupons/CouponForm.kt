package com.example.unieventos.ui.components.coupons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unieventos.enums.CouponCodeError
import com.example.unieventos.enums.CouponNameError
import com.example.unieventos.enums.DateError
import com.example.unieventos.ui.components.utils.DateField

@Composable
fun CouponForm(
    name: String,
    nameError: CouponNameError,
    onNameChange: (String) -> Unit,
    code: String,
    codeError: CouponCodeError,
    onCodeChange: (String) -> Unit,
    discount: String,
    onDiscountSelected: (String) -> Unit,
    expandedDiscount: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    expirationDate: String,
    onExpirationDateChange: (String) -> Unit,
    isDatePicked: Boolean,
    onDatePickedChange: (Boolean) -> Unit,
    dateError: DateError
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        CouponNameField(
            name = name,
            onNameChange = onNameChange,
            nameError = nameError,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        CouponCodeField(
            code = code,
            onCodeChange = onCodeChange,
            codeError = codeError,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        CouponDiscountField(
            discount = discount,
            expanded = expandedDiscount,
            onExpandedChange = { onExpandedChange(it) },
            onDiscountSelected = onDiscountSelected,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        DateField(
            date = expirationDate,
            onDateChange = onExpirationDateChange,
            isDatePicked = isDatePicked,
            onDatePickedChange = onDatePickedChange,
            modifier = Modifier
                .fillMaxWidth(),
            dateError = dateError
        )
    }
}
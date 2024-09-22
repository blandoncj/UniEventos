package com.example.unieventos.ui.components.coupons

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R
import com.example.unieventos.enums.CouponNameError

/**
 * CouponNameField is a composable that displays a text field for the user to input the coupon's name.
 * @param name The name that the user has input.
 * @param onNameChange The function that is called when the user changes the name.
 * @param nameError The error that is displayed when the name is invalid.
 * @param modifier The modifier for the CouponNameField.
 */
@Composable
fun CouponNameField(
    name: String,
    onNameChange: (String) -> Unit,
    nameError: CouponNameError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = stringResource(id = R.string.nam_lbl)) },
        singleLine = true,
        isError = nameError != CouponNameError.NONE,
        supportingText = {
            when (nameError) {
                CouponNameError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field)
                )

                CouponNameError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.nam_length_err)
                )

                CouponNameError.ALREADY_EXISTS -> Text(
                    text = stringResource(id = R.string.cpn_nam_exists_err)
                )

                CouponNameError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
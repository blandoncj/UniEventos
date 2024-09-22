package com.example.unieventos.ui.components.coupons

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.unieventos.R
import com.example.unieventos.enums.CouponCodeError

/**
 * A field to input a coupon code.
 * @param code The current value of the code.
 * @param onCodeChange The action to perform when the code is changed.
 * @param codeError The error state of the code.
 * @param modifier The modifier to apply to the field.
 */
@Composable
fun CouponCodeField(
    code: String,
    onCodeChange: (String) -> Unit,
    codeError: CouponCodeError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = code,
        onValueChange = onCodeChange,
        label = { Text(text = stringResource(id = R.string.cod_lbl)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isError = codeError != CouponCodeError.NONE,
        supportingText = {
            when (codeError) {
                CouponCodeError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field)
                )

                CouponCodeError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.cpn_cod_length_err)
                )

                CouponCodeError.ALREADY_EXISTS -> Text(
                    text = stringResource(id = R.string.cpn_cod_exists_err)
                )

                CouponCodeError.NOT_FOUND -> Text(
                    text = stringResource(id = R.string.cod_found_err)
                )

                CouponCodeError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
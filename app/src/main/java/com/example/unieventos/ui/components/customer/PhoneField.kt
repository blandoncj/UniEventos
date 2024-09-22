package com.example.unieventos.ui.components.customer

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.unieventos.R
import com.example.unieventos.enums.PhoneError

/**
 * PhoneField is a composable that displays a text field for the user to input their phone number.
 * @param phone The phone number that the user has input.
 * @param onPhoneChange The callback that is called when the user changes the phone number.
 * @param phoneError The error that should be displayed, if any.
 * @param modifier The modifier to be applied to the PhoneField.
 */
@Composable
fun PhoneField(
    phone: String,
    onPhoneChange: (String) -> Unit,
    phoneError: PhoneError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = phone,
        onValueChange = onPhoneChange,
        label = { Text(text = stringResource(id = R.string.pho_lbl)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        isError = phoneError != PhoneError.NONE,
        supportingText = {
            when (phoneError) {
                PhoneError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                PhoneError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.pho_length_err),
                    color = Color.Red
                )

                PhoneError.ALREADY_REGISTERED -> Text(
                    text = stringResource(id = R.string.pho_exists_err),
                    color = Color.Red
                )

                PhoneError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
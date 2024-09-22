package com.example.unieventos.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.unieventos.R
import com.example.unieventos.enums.EmailError

/**
 * EmailField is a composable that represents a text field for the email input.
 * @param email The email value.
 * @param onEmailChange The callback that is called when the email value changes.
 * @param emailError The error state of the email field.
 * @param modifier The modifier for the email field.
 */
@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: EmailError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(text = stringResource(id = R.string.ema_lbl)) },
        singleLine = true,
        isError = emailError != EmailError.NONE,
        supportingText = {
            when (emailError) {
                EmailError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                EmailError.INVALID_FORMAT -> Text(
                    text = stringResource(id = R.string.ema_format_err),
                    color = Color.Red
                )

                EmailError.ALREADY_REGISTERED -> Text(
                    text = stringResource(id = R.string.ema_exists_err),
                    color = Color.Red
                )

                EmailError.NONE -> {}
            }
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
    )
}
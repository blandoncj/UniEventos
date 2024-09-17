package com.example.unieventos.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.unieventos.R
import com.example.unieventos.enums.PasswordError

/**
 * A password field with a label, password, and a trailing icon to toggle password visibility.
 * @param label The label of the password field.
 * @param password The password value.
 * @param onPasswordChange The callback to be called when the password value changes.
 * @param passwordError The error state of the password field.
 * @param modifier The modifier to be applied to the password field.
 */
@Composable
fun PasswordField(
    label: String = stringResource(id = R.string.password_lbl),
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: PasswordError,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = label) },
        singleLine = true,
        isError = passwordError != PasswordError.NONE,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon =
                if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(icon, contentDescription = "Toggle password visibility")
            }
        },
        supportingText = {
            when (passwordError) {
                PasswordError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                PasswordError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.password_length),
                    color = Color.Red
                )

                PasswordError.INCORRECT -> Text(
                    text = stringResource(id = R.string.passwords_dont_match),
                    color = Color.Red
                )

                PasswordError.NONE -> {}
            }
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
    )
}
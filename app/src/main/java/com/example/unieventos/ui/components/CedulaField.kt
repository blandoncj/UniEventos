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
import com.example.unieventos.enums.CedulaError

/**
 * A composable that displays a text field for the user to input their cedula.
 * @param cedula The cedula that the user has input.
 * @param onCedulaChange A lambda that is called when the user changes the cedula.
 * @param cedulaError The error state of the cedula field.
 * @param modifier The modifier to be applied to the text field.
 */
@Composable
fun CedulaField(
    cedula: String,
    onCedulaChange: (String) -> Unit,
    cedulaError: CedulaError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = cedula,
        onValueChange = onCedulaChange,
        label = { Text(text = stringResource(id = R.string.cedula_lbl)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isError = cedulaError != CedulaError.NONE,
        supportingText = {
            when (cedulaError) {
                CedulaError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                CedulaError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.cedula_length),
                    color = Color.Red
                )

                CedulaError.ALREADY_REGISTERED -> Text(
                    text = stringResource(id = R.string.cedula_already_exists),
                    color = Color.Red
                )

                CedulaError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
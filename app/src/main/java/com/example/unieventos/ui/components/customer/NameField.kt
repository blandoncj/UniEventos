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
import com.example.unieventos.enums.NameError

/**
 * NameField is a composable that displays a text field for the user to input their name.
 * @param name The name that the user has input.
 * @param onNameChange The function that is called when the user changes the name.
 * @param nameError The error that is displayed when the name is invalid.
 * @param modifier The modifier for the NameField.
 */
@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit,
    nameError: NameError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = stringResource(id = R.string.nam_lbl)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        isError = nameError != NameError.NONE,
        supportingText = {
            when (nameError) {
                NameError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                NameError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.nam_length_err),
                    color = Color.Red
                )

                NameError.INVALID_FORMAT -> Text(
                    text = stringResource(id = R.string.nam_format_err),
                    color = Color.Red
                )

                NameError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
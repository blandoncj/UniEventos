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
import com.example.unieventos.enums.CodeError

/**
 * A composable that displays a text field for the user to input their code.
 * @param code The code that the user has input.
 * @param onCodeChange A lambda that is called when the user changes the code.
 * @param codeError The error state of the code field.
 * @param modifier The modifier to be applied to the text field.
 */
@Composable
fun CodeField(
    code: String,
    onCodeChange: (String) -> Unit,
    codeError: CodeError,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = code,
        onValueChange = onCodeChange,
        label = { Text(text = stringResource(id = R.string.code_lbl)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isError = codeError != CodeError.NONE,
        supportingText = {
            when (codeError) {
                CodeError.EMPTY -> Text(
                    text = stringResource(id = R.string.empty_field),
                    color = Color.Red
                )

                CodeError.INVALID_LENGTH -> Text(
                    text = stringResource(id = R.string.code_length),
                    color = Color.Red
                )

                CodeError.NOT_FOUND -> Text(
                    text = stringResource(id = R.string.code_not_found),
                    color = Color.Red
                )

                CodeError.NONE -> {}
            }
        },
        modifier = modifier
    )
}
package com.example.unieventos.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A primary button with a black background and white text.
 * @param text The text to display on the button.
 * @param enabled Whether the button should be enabled.
 * @param onClick The action to perform when the button is clicked.
 * @param modifier The modifier to apply to the button.
 */
@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}
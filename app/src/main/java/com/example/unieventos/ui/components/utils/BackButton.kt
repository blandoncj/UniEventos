package com.example.unieventos.ui.components.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

/**
 * A back button that navigates back to the previous screen.
 * @param onClick The action to perform when the button is clicked.
 */
@Composable
fun BackButton(
    onBack: () -> Unit = {},
) {
    IconButton(
        onClick = { onBack() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back"
        )
    }
}
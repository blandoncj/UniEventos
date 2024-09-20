package com.example.unieventos.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A back button that navigates back to the previous screen.
 * @param onClick The action to perform when the button is clicked.
 */
@Composable
fun BackButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}
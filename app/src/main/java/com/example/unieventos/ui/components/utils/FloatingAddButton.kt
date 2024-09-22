package com.example.unieventos.ui.components.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingAddButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFFF1E6F9)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add",
            modifier = Modifier.size(35.dp),
            tint = Color.Black
        )
    }
}
package com.example.unieventos.ui.components.utils

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

/**
 * MainTopBar is a custom top bar that is used in the main screen of the app.
 *
 * @param title The title of the top bar.
 * @param hazeState The HazeState that is used to animate the top bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    hazeState: HazeState
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .hazeChild(hazeState),
        title = {
            Text(text = title)
        }
    )
}
package com.example.ui.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppBrandTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors, // TODO add custom colors
        typography = MaterialTheme.typography, // TODO add custom typography
        shapes = MaterialTheme.shapes, // TODO add custom shapes
        content = content
    )
}

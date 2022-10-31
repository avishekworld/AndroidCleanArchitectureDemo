package com.example.ui.compose.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val mediumDimen = 24.dp

fun Modifier.padding() = paddingMedium()
fun Modifier.paddingMedium() = padding(all = mediumDimen)

fun Modifier.paddingHorizontalMedium() = padding(horizontal = mediumDimen)
fun Modifier.paddingVerticalMedium() = padding(horizontal = mediumDimen)

fun Modifier.paddingStartMedium() = padding(start = mediumDimen)

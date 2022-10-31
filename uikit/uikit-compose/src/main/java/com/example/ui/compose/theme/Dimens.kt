package com.example.ui.compose.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val mediumDimen = 24.dp
val smallDimen = 8.dp

fun Modifier.padding() = paddingMedium()
fun Modifier.paddingMedium() = padding(all = mediumDimen)
fun Modifier.paddingSmall() = padding(all = smallDimen)

fun Modifier.paddingHorizontalMedium() = padding(horizontal = mediumDimen)
fun Modifier.paddingVerticalMedium() = padding(horizontal = mediumDimen)

fun Modifier.paddingStartMedium() = padding(start = mediumDimen)
fun Modifier.paddingEndMedium() = padding(end = mediumDimen)

package com.example.cleanarchitecturedemo.feature.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.compose.theme.paddingHorizontalMedium
import com.example.ui.compose.widget.UISpacer

@Composable
fun WeatherScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        Column {

            Text(
                modifier = Modifier.paddingHorizontalMedium(),
                text = "Location Name: Location"
            )

            UISpacer()

            Text(
                modifier = Modifier.paddingHorizontalMedium(),
                text = "Date: Current Date"
            )
        }
    }
}

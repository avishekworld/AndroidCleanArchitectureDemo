package com.example.cleanarchitecturedemo.feature.weather

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecturedemo.views.core.ViewState
import com.example.domain.weather.WeatherData
import com.example.ui.compose.theme.paddingEndMedium
import com.example.ui.compose.theme.paddingHorizontalMedium
import com.example.ui.compose.theme.paddingSmall
import com.example.ui.compose.theme.paddingStartMedium
import com.example.ui.compose.widget.ProcessingView
import com.example.ui.compose.widget.UISpacer

@Composable
fun WeatherScreen(
    viewState: WeatherViewState
) {
    viewState.weatherDetails?.let { weatherDetails ->
        Box(modifier = Modifier.fillMaxSize()) {

            Column {

                Text(
                    modifier = Modifier.paddingHorizontalMedium(),
                    text = "Location Name: ${weatherDetails.locationName}"
                )

                UISpacer(modifier = Modifier.paddingSmall())

                Text(
                    modifier = Modifier.paddingHorizontalMedium(),
                    text = "Date: ${weatherDetails.dateTimeStamp}"
                )

                UISpacer(modifier = Modifier.paddingSmall())

                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(weatherDetails.hourlyUpdate) { item: WeatherData ->
                        HourlyWeather(weatherData = item)
                    }
                }
            }
        }
    }

    ProcessingView(show = viewState.processingViewState is ViewState.Show)
}

@Composable
private fun HourlyWeather(weatherData: WeatherData) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
    ) {

        Column() {
            Text(
                modifier = Modifier
                    .paddingStartMedium()
                    .paddingEndMedium(),
                text = "Date: ${weatherData.dateTimeStamp}"
            )

            Text(
                modifier = Modifier
                    .paddingStartMedium()
                    .paddingEndMedium(),
                text = "Summary: ${weatherData.summary}"
            )

            Text(
                modifier = Modifier
                    .paddingStartMedium()
                    .paddingEndMedium(),
                text = "Temperature: ${weatherData.temperature}"
            )

            UISpacer(modifier = Modifier.paddingSmall())
        }
    }
}

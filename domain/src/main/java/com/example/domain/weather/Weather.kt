package com.example.domain.weather

import com.example.domain.core.SuspendUseCase
import com.example.domain.models.Result

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) :
    SuspendUseCase<Geolocation, Result<WeatherDetails>> {

    override suspend fun run(location: Geolocation): Result<WeatherDetails> {
        return weatherRepository.getWeather(location)
    }
}

interface WeatherRepository {
    suspend fun getWeather(location: Geolocation): Result<WeatherDetails>
}

data class Geolocation(
    val latitude: Double,
    val longitude: Double,
)

data class WeatherDetails(
    val locationName: String,
    val latitude: Double,
    val longitude: Double,
    val dateTimeStamp: String,
    val hourlyUpdate: List<WeatherData> = emptyList(),
    val dailyUpdate: List<WeatherData> = emptyList(),
)

data class WeatherData(
    val summary: String,
    val description: String = "",
    val dateTimeStamp: String,
    val temperature: String,
    val winds: String = "",
    val precipitation: String = "",
    val humidity: String = "",

)

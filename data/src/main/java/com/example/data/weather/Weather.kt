package com.example.data.weather

import com.example.domain.core.CoroutineDispatchers
import com.example.domain.models.Result
import com.example.domain.weather.Geolocation
import com.example.domain.weather.WeatherData
import com.example.domain.weather.WeatherDetails
import com.example.domain.weather.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val coroutineDispatchers: CoroutineDispatchers
) : WeatherRepository {
    override suspend fun getWeather(location: Geolocation): Result<WeatherDetails> = withContext(coroutineDispatchers.io) {
        weatherApi.getWeather(location)
    }
}

interface WeatherApi {
    suspend fun getWeather(location: Geolocation): Result<WeatherDetails>
}

class WeatherApiImpl : WeatherApi {

    // TODO fetch data from weather api
    override suspend fun getWeather(location: Geolocation): Result<WeatherDetails> {
        delay(2000)
        return Result.Success(
            WeatherDetails(
                locationName = "Seattle",
                latitude = location.latitude,
                longitude = location.longitude,
                dateTimeStamp = "2022-10-31 10 am",
                hourlyUpdate = listOf(
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 11 am to 12 pm",
                        temperature = "12 degree C",
                        winds = "From 340 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 12 am to 1 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 12 am to 1 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 12 am to 1 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 2 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 3 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 4 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 5 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 6 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 7 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    ),
                    WeatherData(
                        summary = "Cloudy",
                        dateTimeStamp = "2022-10-31 8 pm",
                        temperature = "10 degree C",
                        winds = "From 330 at 4 MPH"
                    )
                )
            )
        )
    }
}

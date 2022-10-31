package com.example.domain.di

import com.example.domain.core.AppCoroutineDispatchers
import com.example.domain.core.CoroutineDispatchers
import com.example.domain.logger.Logger
import com.example.domain.logger.LoggerImpl
import com.example.domain.usecases.GetUseProfileUseCase
import com.example.domain.weather.GetWeatherUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        GetUseProfileUseCase(get())
    }

    single {
        GetWeatherUseCase(weatherRepository = get())
    }

    single<CoroutineDispatchers> {
        AppCoroutineDispatchers()
    }

    single<Logger> {
        LoggerImpl()
    }
}

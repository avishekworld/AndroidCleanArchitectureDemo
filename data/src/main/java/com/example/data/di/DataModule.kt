package com.example.data.di

import com.example.data.api.OnBoardDataApi
import com.example.data.api.OnBoardDataApiImpl
import com.example.data.api.OnBoardDataRepository
import com.example.data.api.OnBoardDataRepositoryImpl
import com.example.data.api.UserProfileApiImpl
import com.example.data.cache.UserProfileCache
import com.example.data.cache.UserProfileCacheInMemory
import com.example.data.repository.UserProfileRepositoryImpl
import com.example.data.weather.WeatherApi
import com.example.data.weather.WeatherApiImpl
import com.example.data.weather.WeatherRepositoryImpl
import com.example.domain.api.UserProfileApi
import com.example.domain.repository.UserProfileRepository
import com.example.domain.weather.WeatherRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserProfileApi> {
        UserProfileApiImpl()
    }

    single<UserProfileCache> {
        UserProfileCacheInMemory()
    }

    single<UserProfileRepository> {
        UserProfileRepositoryImpl(get(), get())
    }

    single<OnBoardDataApi> {
        OnBoardDataApiImpl()
    }

    single<OnBoardDataRepository> {
        OnBoardDataRepositoryImpl(get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(weatherApi = get(), coroutineDispatchers = get())
    }

    single<WeatherApi> {
        WeatherApiImpl()
    }
}

package com.example.domain.di

import com.example.domain.usecases.GetUseProfileUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        GetUseProfileUseCase(get())
    }
}

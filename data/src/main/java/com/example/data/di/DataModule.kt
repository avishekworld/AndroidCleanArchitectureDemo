package com.example.data.di

import com.example.data.api.UserProfileApiImpl
import com.example.data.cache.UserProfileCache
import com.example.data.cache.UserProfileCacheInMemory
import com.example.data.repository.UserProfileRepositoryImpl
import com.example.domain.api.UserProfileApi
import com.example.domain.repository.UserProfileRepository
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
}
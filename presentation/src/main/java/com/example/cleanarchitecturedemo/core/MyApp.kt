package com.example.cleanarchitecturedemo.core

import android.app.Application
import com.example.cleanarchitecturedemo.di.presentationModule
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            loadKoinModules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}

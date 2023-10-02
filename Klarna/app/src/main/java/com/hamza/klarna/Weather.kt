package com.hamza.klarna

import android.app.Application
import com.hamza.domain.di.weatherDomainModule
import com.hamza.network.di.networkModule
import com.hamza.presentation.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Weather: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Weather)
            modules(listOf(networkModule, weatherDomainModule, weatherModule))
        }
    }
}
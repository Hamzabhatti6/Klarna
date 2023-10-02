package com.hamza.domain.di

import com.hamza.api.repo.WeatherRepository
import com.hamza.api.usecase.WeatherUseCase
import com.hamza.domain.repo.WeatherRepositoryImpl
import com.hamza.domain.usecase.WeatherUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val weatherDomainModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<WeatherUseCase> { WeatherUseCaseImpl(coroutineContext = Dispatchers.IO, get()) }
}
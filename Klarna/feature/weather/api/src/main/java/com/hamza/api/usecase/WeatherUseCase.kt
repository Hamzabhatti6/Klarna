package com.hamza.api.usecase

import com.hamza.api.Weather

interface WeatherUseCase {
    suspend operator fun invoke(lat: String, long: String, currentWeather:  Boolean): WeatherUseCaseResult
}
sealed class WeatherUseCaseResult {
    data class Success(val weather: Weather): WeatherUseCaseResult()
    data class Error(val throwable: Throwable?): WeatherUseCaseResult()
}
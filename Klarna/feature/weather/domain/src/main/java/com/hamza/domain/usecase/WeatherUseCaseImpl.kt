package com.hamza.domain.usecase

import com.hamza.api.repo.WeatherRepository
import com.hamza.api.usecase.WeatherUseCase
import com.hamza.api.usecase.WeatherUseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class WeatherUseCaseImpl(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {
    override suspend fun invoke(lat: String, long: String, currentWeather:  Boolean): WeatherUseCaseResult {
        return withContext(coroutineContext) {
            try {
                val weather = weatherRepository.fetchWeather(lat, long, currentWeather)
                WeatherUseCaseResult.Success(weather)
            } catch (e: Exception) {
                WeatherUseCaseResult.Error(e.cause)
            }
        }
    }
}
package com.hamza.domain.repo

import com.hamza.api.Weather
import com.hamza.api.repo.WeatherRepository
import com.hamza.domain.extention.toWeather
import com.hamza.network.ApiService

class WeatherRepositoryImpl(private val apiService: ApiService): WeatherRepository {
    override suspend fun fetchWeather(lat: String, long: String, currentWeather: Boolean): Weather {
        return apiService.fetchWeather(lat, long, currentWeather).toWeather()
    }
}
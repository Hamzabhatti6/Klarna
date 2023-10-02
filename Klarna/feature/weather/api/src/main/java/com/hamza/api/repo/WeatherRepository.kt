package com.hamza.api.repo

import com.hamza.api.Weather

interface WeatherRepository {
    suspend fun fetchWeather(lat: String, long: String, currentWeather: Boolean): Weather
}
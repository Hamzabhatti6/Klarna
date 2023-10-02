package com.hamza.network

import com.hamza.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun fetchWeather(@Query("latitude") lat: String, @Query("longitude") long : String, @Query("current_weather") currentWeather: Boolean): WeatherDto
}
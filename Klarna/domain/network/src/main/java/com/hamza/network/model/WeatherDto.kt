package com.hamza.network.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(@SerializedName("latitude") val latitude : Double,
                      @SerializedName("longitude") val longitude : Double,
                      @SerializedName("current_weather") val currentWeather : CurrentWeatherDto,
                      @SerializedName("current_weather_units") val currentWeatherUnits : CurrentWeatherUnitsDto,
)

data class CurrentWeatherDto(
    @SerializedName("time") val time : String,
    @SerializedName("temperature") val temperature : Double,
    @SerializedName("windspeed") val windspeed : Double,
    @SerializedName("weathercode") val weathercode : Int
)
data class CurrentWeatherUnitsDto(
    @SerializedName("temperature") val temperature : String,
    @SerializedName("windspeed") val windspeed : String,
    @SerializedName("weathercode") val weathercode : String
)

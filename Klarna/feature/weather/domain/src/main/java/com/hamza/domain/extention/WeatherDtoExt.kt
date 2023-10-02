package com.hamza.domain.extention

import com.hamza.api.Weather
import com.hamza.network.model.WeatherDto

fun WeatherDto.toWeather() : Weather {
    return Weather(
        latitude = this.latitude.toString(),
        longitude = this.longitude.toString(),
        temperatureUnit = this.currentWeatherUnits.temperature,
        windspeedUnit = this.currentWeatherUnits.windspeed,
        time = this.currentWeather.time,
        windspeed = this.currentWeather.windspeed.toString(),
        temperature = this.currentWeather.temperature.toString())
}

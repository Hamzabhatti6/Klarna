package com.hamza.presentation.ui.viewmodel

import com.hamza.api.usecase.WeatherUseCase
import com.hamza.api.usecase.WeatherUseCaseResult
import com.sample.common.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.hamza.api.Weather

class WeatherViewModel (val getWeatherUseCase: WeatherUseCase): BaseViewModel() {

    private val _weatherState = MutableStateFlow(WeatherState())
    val weather: StateFlow<WeatherState> = _weatherState.asStateFlow()
    private val lat = "52.507929"
    private val long = "13.400848"
    private val currentWeather = true

    init {
        loadWeather()
    }

    private fun loadWeather() {
        fetchData(lat, long, currentWeather)
    }

    fun retry() {
        fetchData(lat, long, currentWeather)
    }

    private fun fetchData(lat: String, long: String, currentWeather:  Boolean) {
        viewModelScope.launch {
            _weatherState.update { WeatherState ->
                WeatherState.copy(isLoading = true)
            }
            viewModelScope.launch {
                when(val weatherData = getWeatherUseCase(lat, long, currentWeather)) {
                    is WeatherUseCaseResult.Success -> {
                        _weatherState.update { weatherState ->
                            weatherState.copy(isLoading = false, isError = false, errorMessage = null, weather = weatherData.weather)
                        }
                    }
                    is WeatherUseCaseResult.Error -> {
                        _weatherState.update { WeatherState ->
                            WeatherState.copy(isLoading = false, isError = true, errorMessage = weatherData.throwable?.message)
                        }
                    }
                    else -> {
                        _weatherState.update { WeatherState ->
                            WeatherState.copy(isLoading = false, isError = true)
                        }
                    }
                }
            }
        }
    }
}

data class WeatherState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val weather: Weather =  Weather()
)
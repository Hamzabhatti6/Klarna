package com.hamza.domain.usecase

import com.hamza.api.Weather
import com.hamza.api.usecase.WeatherUseCase
import com.hamza.api.usecase.WeatherUseCaseResult
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class WeatherUseCaseImplTest {
    private val lat = "52.507929"
    private val long = "13.400848"
    private val currentWeather = true

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `verify success` () = runBlocking {
        val mockWeather = mockk<Weather>()
        val useCase = object : WeatherUseCase {
            override suspend fun invoke(lat: String, long: String, currentWeather: Boolean): WeatherUseCaseResult = WeatherUseCaseResult.Success(mockWeather)
        }

        val result = useCase(lat, long, currentWeather)

        assertEquals(WeatherUseCaseResult.Success(mockWeather), result)
    }

    @Test
    fun `verify error`() = runBlocking {
        val errorMessage = "An error occurred"
        val throwable = Throwable(errorMessage)
        val useCase = object : WeatherUseCase {
            override suspend fun invoke(lat: String, long: String, currentWeather: Boolean): WeatherUseCaseResult = WeatherUseCaseResult.Error(throwable)
        }

        val result = useCase(lat, long, currentWeather)

        assertEquals(WeatherUseCaseResult.Error(throwable), result)
    }
}
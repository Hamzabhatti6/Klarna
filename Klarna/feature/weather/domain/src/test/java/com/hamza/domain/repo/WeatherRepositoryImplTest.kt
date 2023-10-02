package com.hamza.domain.repo

import com.hamza.api.repo.WeatherRepository
import com.hamza.network.ApiService
import com.hamza.network.model.WeatherDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class WeatherRepositoryImplTest {

    @MockK
    private lateinit var apiService: ApiService
    private lateinit var repository: WeatherRepository
    private val lat = "52.507929"
    private val long = "13.400848"
    private val currentWeather = true

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = WeatherRepositoryImpl(apiService)
    }

    @Test
    fun `verify weather call`() = runBlocking {
        coEvery { apiService.fetchWeather(lat, long, currentWeather) } returns (mockk<WeatherDto>(relaxed = true))

        repository.fetchWeather(lat, long, currentWeather)

        coVerify { apiService.fetchWeather(lat, long, currentWeather) }
    }
}
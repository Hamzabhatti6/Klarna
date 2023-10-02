package com.hamza.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.hamza.api.Weather
import com.hamza.api.usecase.WeatherUseCase
import com.hamza.api.usecase.WeatherUseCaseResult
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private lateinit var getWeatherUseCase: WeatherUseCase

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val lat = "52.507929"
    private val long = "13.400848"
    private val currentWeather = true

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        getWeatherUseCase = mockk<WeatherUseCase>()
    }
    @Test
    fun `verify data is loaded`() = runTest {
        coEvery { getWeatherUseCase(lat = lat, long = long, currentWeather =currentWeather) } returns WeatherUseCaseResult.Success(weather = Weather())
        val viewModel = WeatherViewModel(getWeatherUseCase)

        viewModel.weather.test {
            assertEquals(WeatherState(), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify data is loaded and mapped to UI Model`() = runTest {
        val weather = Weather()
        coEvery { getWeatherUseCase(lat = lat, long = long, currentWeather =currentWeather) } returns WeatherUseCaseResult.Success(
            weather =  weather
        )
        val viewModel = WeatherViewModel(getWeatherUseCase)

        viewModel.weather.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(weather, state.weather)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading`() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getWeatherUseCase(lat = lat, long = long, currentWeather =currentWeather) } returns WeatherUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = WeatherViewModel(getWeatherUseCase)

        viewModel.weather.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
         //   assertTrue(state.weather.isEmpty())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading and retry success `() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getWeatherUseCase(lat = lat, long = long, currentWeather =currentWeather) } returns WeatherUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = WeatherViewModel(getWeatherUseCase)

        viewModel.weather.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            cancelAndConsumeRemainingEvents()
        }

        val weather = Weather()
        coEvery { getWeatherUseCase(lat = lat, long = long, currentWeather =currentWeather) } returns WeatherUseCaseResult.Success(
            weather =  weather
        )

        viewModel.retry()

        viewModel.weather.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(weather, state.weather)
            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

}
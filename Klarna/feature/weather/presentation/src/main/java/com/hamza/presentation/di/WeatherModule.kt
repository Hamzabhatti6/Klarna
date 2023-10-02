package com.hamza.presentation.di

import com.hamza.presentation.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    viewModel { WeatherViewModel(get()) }
}
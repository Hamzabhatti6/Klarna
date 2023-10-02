package com.hamza.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.hamza.presentation.ui.composable.WeatherScreen
import com.hamza.presentation.ui.viewmodel.WeatherViewModel
import com.sample.common.AppTheme
import org.koin.android.ext.android.inject

class WeatherActivity : AppCompatActivity() {

    private val viewModel by inject<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                WeatherScreen(viewModel)
            }
        }
    }
}
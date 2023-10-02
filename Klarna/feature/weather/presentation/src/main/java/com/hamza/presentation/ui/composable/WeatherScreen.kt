package com.hamza.presentation.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamza.api.Weather
import com.hamza.presentation.R
import com.hamza.presentation.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val weatherState by viewModel.weather.collectAsState()

    // Simulate loading state
    val isLoading by rememberUpdatedState(newValue = weatherState.isLoading)
    val isError by rememberUpdatedState(newValue = weatherState.isError)
    val errorMessage by rememberUpdatedState(newValue = weatherState.errorMessage)

    Scaffold(
    content = { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (isError) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Snackbar(
                            modifier = Modifier.padding(16.dp),
                            action = {
                                TextButton(
                                    onClick = {
                                        viewModel.retry()
                                    }
                                ) {
                                    Text(text = "Retry")
                                }
                            }
                        ) {
                            Text(text = errorMessage ?: "An error occurred")
                        }
                    }
                }
                else {
                    WeatherColumn(weather = weatherState.weather)
                }
            }
        }
    }
    )
}

@Composable
fun WeatherColumn(weather: Weather) {
    Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
                painterResource(R.drawable.ic_cloudy_day),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp).padding(0.dp,10.dp,0.dp,0.dp)
            )

        Row {
            Text("Temperature",   fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.temperature!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.temperatureUnit!!,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp,8.dp,0.dp,0.dp)
            )
        }
        Row {
            Text("Wind Speed",   fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.windspeed!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.windspeedUnit!!,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp,8.dp,0.dp,0.dp)
            )
        }

        Row {
            Text("Latitude",   fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.latitude!!,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Row {
            Text("Longitude",   fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = weather.longitude!!,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

}
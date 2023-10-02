package com.hamza.api

data class Weather(val latitude : String? = null,
                   val longitude : String? = null,
                   val temperatureUnit : String? = null,
                   val windspeedUnit : String? = null,
                   val time : String? = null,
                   val windspeed : String? = null,
                   val temperature : String? = null)

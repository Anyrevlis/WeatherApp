package com.example.weatherapp.model

import android.graphics.drawable.Drawable

data class HourlyForecast(
    val time: String,
    val icon: Drawable,
    val temperature: Int
)

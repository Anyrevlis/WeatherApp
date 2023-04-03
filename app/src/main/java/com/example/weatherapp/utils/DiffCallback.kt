package com.example.weatherapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.model.WeatherResponse

class DiffCallback : DiffUtil.ItemCallback<WeatherResponse>() {
    override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
        return oldItem == newItem
    }
}



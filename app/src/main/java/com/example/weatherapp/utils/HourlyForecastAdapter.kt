package com.example.weatherapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ForecastitemBinding
import com.example.weatherapp.model.HourlyForecast

class HourlyForecastAdapter(private val forecastList: List<HourlyForecast>) : RecyclerView.Adapter<HourlyForecastAdapter.ForecastViewHolder>(){

    inner class ForecastViewHolder(private val binding: ForecastitemBinding) : RecyclerView.ViewHolder(binding.root) {
        val timeTextView: TextView = binding.timeTextView
        val weatherIconImageView: ImageView = binding.weatherIconImageView
        val temperatureTextView: TextView = binding.temperatureTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ForecastitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.timeTextView.text = forecast.time
        // holder.weatherIconImageView.setImageResource(forecast.weatherIcon)
        holder.temperatureTextView.text = "${forecast.temperature}°"
    }
}

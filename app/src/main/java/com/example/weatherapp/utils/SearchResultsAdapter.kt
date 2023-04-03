package com.example.weatherapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.SearchResultItemBinding
import com.example.weatherapp.model.WeatherResponse
import java.util.*

class SearchResultsAdapter(diffCallback: DiffUtil.ItemCallback<WeatherResponse>) : ListAdapter<WeatherResponse, SearchResultsAdapter.SearchResultViewHolder>(diffCallback), Filterable {

    private var searchResults: List<WeatherResponse> = emptyList()
    private var filteredSearchResults: List<WeatherResponse> = emptyList()

    inner class SearchResultViewHolder(private val binding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherResponse: WeatherResponse) {
            binding.cityNameTextView.text = weatherResponse.location?.name
            binding.currentTimeTextView.text = weatherResponse.location?.localtime
            binding.timezoneTextView.text = weatherResponse.location?.tzId
            binding.degreesTextView.text = weatherResponse.current?.tempC.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchResultItemBinding.inflate(layoutInflater, parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredSearchResults.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val weatherResponse = filteredSearchResults[position]
        holder.bind(weatherResponse)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<WeatherResponse>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(searchResults)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (item in searchResults) {
                        if (item.location?.name?.toLowerCase(Locale.ROOT)?.contains(filterPattern) == true) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredSearchResults = results?.values as List<WeatherResponse>
                notifyDataSetChanged()
            }
        }
    }
}



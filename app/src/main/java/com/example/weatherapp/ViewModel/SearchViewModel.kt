package com.example.weatherapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.API.SearchApi
import com.example.weatherapp.model.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableLiveData<List<WeatherResponse>>()
    val searchResults: LiveData<List<WeatherResponse>> = _searchResults
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val myApi = retrofit.create(SearchApi::class.java)

    fun fetchData() {
        coroutineScope.launch {
            try {
                val response = myApi.getData()
                if (response.isSuccessful) {
                    val weatherResponseList = response.body()
                    if (weatherResponseList != null) {
                        // Parse the JSON response into a list of WeatherResponse objects using Gson
                        val gson = Gson()
                        val weatherListJson = gson.toJson(weatherResponseList)
                        val weatherResponseListParsed = gson.fromJson<List<WeatherResponse>>(weatherListJson, object : TypeToken<List<WeatherResponse>>() {}.type)

                        // Update the live data with the parsed data
                        _searchResults.postValue(weatherResponseListParsed)
                    }
                } else {
                    // Handle the error
                    _searchResults.value = null
                }
            } catch (e: Exception) {
                // Handle the exception
                _searchResults.value = null
            }
        }
    }


    fun search(query: String) {
        // You could perform your search logic here, for example by calling a repository
        // that fetches data from a remote server or a local database.
        // For this example, we just return a hard-coded list of city names that match the query.

        coroutineScope.launch {
            try {
                val response = myApi.search(query)
                if (response.isSuccessful) {
                    _searchResults.value = response.body()
                } else {
                    // handle error
                }
            } catch (e: Exception) {
                // handle exception
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}

package com.example.weatherapp.API

import com.example.weatherapp.model.*
import com.example.weatherapp.model.Current
import com.example.weatherapp.model.WeatherResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("data")
    suspend fun getData(): Response<List<WeatherResponse>>

    @GET("search")
    suspend fun search(@Query("q") query: String): Response<List<WeatherResponse>>
}

data class WeatherResponse (

    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("current"  ) var current  : Current?  = Current(),
    @SerializedName("forecast" ) var forecast : Forecast? = Forecast()

)
data class Current (

    @SerializedName("last_updated_epoch" ) var lastUpdatedEpoch : Int?       = null,
    @SerializedName("last_updated"       ) var lastUpdated      : String?    = null,
    @SerializedName("temp_c"             ) var tempC            : Int?       = null,
    @SerializedName("temp_f"             ) var tempF            : Double?    = null,
    @SerializedName("is_day"             ) var isDay            : Int?       = null,
    @SerializedName("condition"          ) var condition        : Condition? = Condition(),
    @SerializedName("wind_mph"           ) var windMph          : Double?    = null,
    @SerializedName("wind_kph"           ) var windKph          : Int?       = null,
    @SerializedName("wind_degree"        ) var windDegree       : Int?       = null,
    @SerializedName("wind_dir"           ) var windDir          : String?    = null,
    @SerializedName("pressure_mb"        ) var pressureMb       : Int?       = null,
    @SerializedName("pressure_in"        ) var pressureIn       : Int?       = null,
    @SerializedName("precip_mm"          ) var precipMm         : Int?       = null,
    @SerializedName("precip_in"          ) var precipIn         : Int?       = null,
    @SerializedName("humidity"           ) var humidity         : Int?       = null,
    @SerializedName("cloud"              ) var cloud            : Int?       = null,
    @SerializedName("feelslike_c"        ) var feelslikeC       : Int?       = null,
    @SerializedName("feelslike_f"        ) var feelslikeF       : Int?       = null,
    @SerializedName("vis_km"             ) var visKm            : Int?       = null,
    @SerializedName("vis_miles"          ) var visMiles         : Int?       = null,
    @SerializedName("uv"                 ) var uv               : Int?       = null,
    @SerializedName("gust_mph"           ) var gustMph          : Double?    = null,
    @SerializedName("gust_kph"           ) var gustKph          : Double?    = null

)
data class Condition (

    @SerializedName("text" ) var text : String? = null,
    @SerializedName("icon" ) var icon : String? = null,
    @SerializedName("code" ) var code : Int?    = null

)
data class Forecast (

    @SerializedName("forecastday" ) var forecastday : ArrayList<ForecastDay> = arrayListOf()

)


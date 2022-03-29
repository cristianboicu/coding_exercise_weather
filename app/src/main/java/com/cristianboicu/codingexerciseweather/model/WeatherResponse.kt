package com.cristianboicu.codingexerciseweather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("list")
    val list: ArrayList<ListResponse>
)

data class ListResponse(
    @SerializedName("weather")
    val weather: ArrayList<Weather>,
    @SerializedName("main")
    val main: Temperature
)

data class Temperature(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
)

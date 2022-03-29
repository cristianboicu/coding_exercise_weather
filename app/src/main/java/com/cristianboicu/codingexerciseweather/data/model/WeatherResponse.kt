package com.cristianboicu.codingexerciseweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("list")
    val list: ArrayList<ListResponse>,
)

data class ListResponse(
    @SerializedName("weather")
    val weather: ArrayList<Weather>,
    @SerializedName("main")
    val main: Temperature,
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

fun asDatabaseModel(rawData: ArrayList<ListResponse>): List<Forecast> {
    val resultList = mutableListOf<Forecast>()
    for (item in rawData) {
        resultList.add(
            Forecast(
                0,
                item.main.temp,
                item.main.feels_like,
                item.weather[0].main,
                item.weather[0].description
            )
        )
    }
    return resultList
}
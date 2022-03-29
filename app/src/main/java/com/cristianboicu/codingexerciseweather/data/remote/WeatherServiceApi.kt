package com.cristianboicu.codingexerciseweather.data.remote

import com.cristianboicu.codingexerciseweather.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceApi {
    //for simplicity I've put api_key here
    @GET("/data/2.5/forecast?")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("APPID") app_id: String = "710b089b90390c9e8d616ccbc3a8478d",
        @Query("units") units: String = "metric",
    ): Response<WeatherResponse>

}
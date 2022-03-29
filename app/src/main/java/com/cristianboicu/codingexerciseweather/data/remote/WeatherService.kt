package com.cristianboicu.codingexerciseweather.data.remote

import com.cristianboicu.codingexerciseweather.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/forecast?")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("APPID") app_id: String = "65d00499677e59496ca2f318eb68c049",
    ): Response<WeatherResponse>

    companion object {
        var retrofitService: WeatherService? = null

        fun getInstance(): WeatherService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(WeatherService::class.java)
            }
            return retrofitService!!
        }
    }
}
package com.cristianboicu.codingexerciseweather.data.remote

import com.cristianboicu.codingexerciseweather.data.model.WeatherResponse
import com.cristianboicu.codingexerciseweather.util.Result
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: WeatherServiceApi) {

    suspend fun getForecast(city: String): Result<WeatherResponse> {
        return try {
            val apiResponse = apiService.getForecast(city)
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(Exception("Unknown error"))
            } else {
                Result.Error(Exception("Unknown error"))
            }
        } catch (e: Exception) {
            Result.Error(Exception("Couldn't reach server. Check your internet connection!"))
        }
    }

}
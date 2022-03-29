package com.cristianboicu.codingexerciseweather.ui.lookup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristianboicu.codingexerciseweather.data.remote.WeatherService
import kotlinx.coroutines.launch

class LookUpViewModel : ViewModel() {
    private val retrofitService = WeatherService.getInstance()

    fun fetchData(city: String) {
        Log.d("LookUpViewModel", city)
        viewModelScope.launch {
            val response = retrofitService.getForecast(city)
            if (response.isSuccessful) {
                response.body()?.let {
                    for (item in it.list) {
                        Log.d("LookUpViewModel", "${item.weather.toString()} + \n")
                        Log.d("LookUpViewModel", "${item.main} - \n")

                    }
//                    Log.d("LookUpViewModel", it.list.toString())
                }

            }
        }
    }
}
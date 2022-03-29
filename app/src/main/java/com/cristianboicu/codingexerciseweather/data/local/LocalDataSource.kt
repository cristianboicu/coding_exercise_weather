package com.cristianboicu.codingexerciseweather.data.local

import com.cristianboicu.codingexerciseweather.data.model.Forecast

class LocalDataSource constructor(private val forecastDao: ForecastDao) {

    suspend fun getData(): List<Forecast> {
        return forecastDao.getData()
    }

    suspend fun deleteData(){
       forecastDao.deleteData()
    }

    suspend fun saveData(data: List<Forecast>){
        forecastDao.saveData(*data.toTypedArray())
    }
}
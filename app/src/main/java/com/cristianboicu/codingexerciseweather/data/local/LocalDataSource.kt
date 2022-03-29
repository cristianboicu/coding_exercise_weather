package com.cristianboicu.codingexerciseweather.data.local

import com.cristianboicu.codingexerciseweather.data.model.Forecast
import com.cristianboicu.codingexerciseweather.util.Result


class LocalDataSource constructor(private val forecastDao: ForecastDao) {

    suspend fun getData(): List<Forecast> {
        return forecastDao.getData()
    }

    suspend fun deleteData() {
        forecastDao.deleteData()
    }

    suspend fun saveData(data: List<Forecast>) {
        forecastDao.saveData(*data.toTypedArray())
    }

    suspend fun getForecastById(id: Int): Result<Forecast> {
        return try {
            val res = forecastDao.getForecastById(id)
            if (res != null) {
                Result.Success(res)
            } else {
                Result.Error(Exception("Forecast not found!"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
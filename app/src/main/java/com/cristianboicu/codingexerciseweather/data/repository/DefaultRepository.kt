package com.cristianboicu.codingexerciseweather.data.repository

import com.cristianboicu.codingexerciseweather.data.local.LocalDataSource
import com.cristianboicu.codingexerciseweather.data.model.Forecast
import com.cristianboicu.codingexerciseweather.data.model.ListResponse
import com.cristianboicu.codingexerciseweather.data.model.asDatabaseModel
import com.cristianboicu.codingexerciseweather.data.remote.RemoteDataSource
import com.cristianboicu.codingexerciseweather.util.Result
import com.cristianboicu.codingexerciseweather.util.Status

class DefaultRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    private suspend fun saveData(data: ArrayList<ListResponse>) {
        val asDomainModel = asDatabaseModel(data)
        localDataSource.saveData(asDomainModel)
    }

    suspend fun getForecastById(id: Int): Result<Forecast> {
        return localDataSource.getForecastById(id)
    }

    suspend fun getData() = localDataSource.getData()
    private suspend fun getForecast(city: String) = remoteDataSource.getForecast(city)

    suspend fun refreshData(city: String): Status {
        return try {
            val response = getForecast(city)
            if (response is Result.Success) {
                localDataSource.deleteData()
                saveData(response.data.list)
                Status.SUCCESS
            } else {
                Status.ERROR
            }
        } catch (e: Exception) {
            Status.ERROR
        }
    }

}
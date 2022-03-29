package com.cristianboicu.codingexerciseweather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cristianboicu.codingexerciseweather.data.model.Forecast

@Dao
interface ForecastDao {
    @Query("select * from forecast")
    suspend fun getData(): List<Forecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(vararg forecast: Forecast)

    @Query("delete from forecast")
    suspend fun deleteData()

    @Query("select * from forecast where id=:id")
    suspend fun getForecastById(id: Int): Forecast
}
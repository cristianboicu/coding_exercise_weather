package com.cristianboicu.codingexerciseweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cristianboicu.codingexerciseweather.data.model.Forecast

@Database(entities = [Forecast::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun getForecastDao(): ForecastDao
}
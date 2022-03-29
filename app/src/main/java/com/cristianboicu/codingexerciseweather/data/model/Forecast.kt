package com.cristianboicu.codingexerciseweather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val temp: Float,
    val feels_like: Float,
    val weather: String,
    val description: String,
)

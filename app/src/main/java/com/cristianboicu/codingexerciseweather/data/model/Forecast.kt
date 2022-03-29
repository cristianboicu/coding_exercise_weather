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

fun List<Forecast>.asDomainModel(): List<DomainForecast>{
    return map {
        it.asDomainModel()
    }
}

fun Forecast.asDomainModel(): DomainForecast {
    return let {
        DomainForecast(
            id = it.id,
            temp = it.temp.toString(),
            feels_like = it.feels_like.toString(),
            weather = it.weather,
            description = it.description
        )
    }
}

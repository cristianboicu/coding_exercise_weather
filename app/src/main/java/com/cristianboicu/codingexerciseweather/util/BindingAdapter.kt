package com.cristianboicu.codingexerciseweather.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cristianboicu.codingexerciseweather.data.model.DomainForecast

@BindingAdapter("domainForecast")
fun TextView.setUserName(item: DomainForecast?) {
    item?.let {
        text = "\n\n${it.temp} \n\n" +
                "Feels like: ${it.feels_like}\n\n" +
                "${it.weather}\n\n" +
                "${it.description}"
    }
}
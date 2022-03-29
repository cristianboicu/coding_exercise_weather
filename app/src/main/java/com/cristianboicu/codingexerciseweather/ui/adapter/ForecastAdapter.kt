package com.cristianboicu.codingexerciseweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cristianboicu.codingexerciseweather.data.model.DomainForecast
import com.cristianboicu.codingexerciseweather.data.model.Forecast
import com.cristianboicu.codingexerciseweather.databinding.ForecastItemBinding

class ForecastAdapter(private val clickListener: ForecastListener) :
    ListAdapter<DomainForecast, ForecastViewHolder>(ForecastDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast, clickListener)
    }
}

class ForecastViewHolder private constructor(private val binding: ForecastItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: DomainForecast,
        clickListener: ForecastListener,
    ) {
        binding.forecast = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ForecastViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ForecastItemBinding.inflate(layoutInflater, parent, false)
            return ForecastViewHolder(binding)
        }
    }
}

class ForecastDiffCallback : DiffUtil.ItemCallback<DomainForecast>() {
    override fun areItemsTheSame(oldItem: DomainForecast, newItem: DomainForecast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DomainForecast, newItem: DomainForecast): Boolean {
        return oldItem == newItem
    }
}

class ForecastListener(val clickListener: (forecastId: Int) -> Unit) {
    fun onClick(forecast: DomainForecast) = clickListener(forecast.id)
}
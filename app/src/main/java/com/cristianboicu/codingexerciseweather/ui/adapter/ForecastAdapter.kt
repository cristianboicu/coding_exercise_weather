package com.cristianboicu.codingexerciseweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cristianboicu.codingexerciseweather.data.model.Forecast
import com.cristianboicu.codingexerciseweather.databinding.ForecastItemBinding

class ForecastAdapter(private val clickListener: ForecastListener) :
    ListAdapter<Forecast, ForecastViewHolder>(ForecastDiffCallback()) {
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
        item: Forecast,
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

class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }
}

class ForecastListener(val clickListener: (forecastId: Int) -> Unit) {
    fun onClick(forecast: Forecast) = clickListener(forecast.id)
}
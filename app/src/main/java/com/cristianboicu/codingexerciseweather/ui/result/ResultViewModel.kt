package com.cristianboicu.codingexerciseweather.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristianboicu.codingexerciseweather.data.model.Forecast
import com.cristianboicu.codingexerciseweather.data.repository.DefaultRepository
import com.cristianboicu.codingexerciseweather.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(defaultRepository: DefaultRepository) : ViewModel() {

    private val _navigateToDetails = MutableLiveData<Event<Int>>()
    val navigateToDetails = _navigateToDetails

    private val _forecastData = MutableLiveData<List<Forecast>>()
    val forecastData = _forecastData

    init {
        viewModelScope.launch {
            _forecastData.value = defaultRepository.getData()
        }
    }

    fun onForecastClicked(it: Int?) {
        it?.let {
            _navigateToDetails.value = Event(it)
        }
    }
}
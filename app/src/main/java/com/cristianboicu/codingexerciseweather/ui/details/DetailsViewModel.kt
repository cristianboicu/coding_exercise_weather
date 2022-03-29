package com.cristianboicu.codingexerciseweather.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristianboicu.codingexerciseweather.data.model.DomainForecast
import com.cristianboicu.codingexerciseweather.data.model.asDomainModel
import com.cristianboicu.codingexerciseweather.data.repository.DefaultRepository
import com.cristianboicu.codingexerciseweather.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val defaultRepository: DefaultRepository) :
    ViewModel() {

    private val _forecastDetails = MutableLiveData<DomainForecast>()
    val forecastDetails: LiveData<DomainForecast>
        get() = _forecastDetails

    fun start(id: Int) {
        viewModelScope.launch {
            val result = defaultRepository.getForecastById(id)
            if (result is Result.Success) {
                withContext(Dispatchers.Default) {
                    _forecastDetails.postValue(result.data.asDomainModel())
                }
            }
        }
    }
}
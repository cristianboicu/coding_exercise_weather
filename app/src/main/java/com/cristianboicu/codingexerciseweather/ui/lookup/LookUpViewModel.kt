package com.cristianboicu.codingexerciseweather.ui.lookup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristianboicu.codingexerciseweather.data.repository.DefaultRepository
import com.cristianboicu.codingexerciseweather.util.Event
import com.cristianboicu.codingexerciseweather.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LookUpViewModel @Inject constructor(private val defaultRepository: DefaultRepository) :
    ViewModel() {

    val navigateToResults = MutableLiveData<Event<Unit>>()

    private val status = MutableLiveData<Status>()

    private fun checkUserInput(city: String): Boolean {
        return !(city.isEmpty() || city.isBlank())
    }

    fun fetchForecast(city: String) {
        if (checkUserInput(city)) {
            viewModelScope.launch {
                status.value = defaultRepository.refreshData(city)
                checkStatus(status.value)
            }
        }
    }

    private fun checkStatus(status: Status?) {
        if (status == Status.SUCCESS) {
            navigateToResults.value = Event(Unit)
        }
    }
}
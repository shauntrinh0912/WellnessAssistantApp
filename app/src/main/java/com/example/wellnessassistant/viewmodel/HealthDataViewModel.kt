package com.example.wellnessassistant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HealthData(
    val steps: Int,
    val calories: Int,
    val sleepHours: Float,
    val mood: String
)

class HealthDataViewModel : ViewModel() {
    private val _healthData = MutableStateFlow(
        HealthData(steps = 5000, calories = 2000, sleepHours = 7.5f, mood = "Good")
    )
    val healthData = _healthData.asStateFlow()

    fun updateHealthData(newData: HealthData) {
        viewModelScope.launch {
            delay(500)
            _healthData.value = newData
        }
    }
}

package com.example.wellnessassistant.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Goals(
    val steps: Int,
    val sleepHours: Float,
    val calories: Int
)

class GoalsViewModel : ViewModel() {
    var goals by mutableStateOf(
        Goals(
            steps = 10000,
            sleepHours = 8f,
            calories = 2200
        )
    )
}

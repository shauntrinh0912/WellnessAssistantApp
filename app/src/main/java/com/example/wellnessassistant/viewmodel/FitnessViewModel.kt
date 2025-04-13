package com.example.wellnessassistant.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class ExerciseSession(
    val type: String,
    val duration: Int,
    val caloriesBurned: Int
)

class FitnessViewModel : ViewModel() {
    val sessions = mutableStateListOf<ExerciseSession>()

    fun addSession(session: ExerciseSession) {
        sessions.add(session)
    }

    fun totalDuration(): Int {
        return sessions.sumOf { it.duration }
    }

    fun totalCaloriesBurned(): Int {
        return sessions.sumOf { it.caloriesBurned }
    }
}

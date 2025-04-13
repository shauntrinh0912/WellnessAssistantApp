package com.example.wellnessassistant.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Meal(
    val name: String,
    val calories: Int,
)

class DietViewModel : ViewModel() {

    val meals = mutableStateListOf<Meal>()

    fun addMeal(meal: Meal) {
        meals.add(meal)
    }

    fun totalCalories(): Int {
        return meals.sumOf { it.calories }
    }
}

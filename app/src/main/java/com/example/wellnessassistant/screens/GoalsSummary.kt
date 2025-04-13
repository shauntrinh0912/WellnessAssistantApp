package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.wellnessassistant.viewmodel.Goals

@Composable
fun GoalsSummary(
    goals: Goals,
    currentSteps: Int,
    currentSleep: Float,
    currentCalories: Int
) {
    // Calculate completion percentages (avoid division by zero)
    val stepsPercent = if (goals.steps > 0) (currentSteps * 100) / goals.steps else 0
    val sleepPercent = if (goals.sleepHours > 0) ((currentSleep / goals.sleepHours) * 100).toInt() else 0
    val caloriesPercent = if (goals.calories > 0) (currentCalories * 100) / goals.calories else 0

    Column(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
        Text("Your Goals:", style = MaterialTheme.typography.titleMedium)
        Text("Steps: $currentSteps / ${goals.steps} ($stepsPercent%)")
        Text("Sleep: $currentSleep / ${goals.sleepHours} hours ($sleepPercent%)")
        Text("Calories: $currentCalories / ${goals.calories} kcal ($caloriesPercent%)")
    }
}

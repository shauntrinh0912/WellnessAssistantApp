package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessassistant.viewmodel.Goals
import com.example.wellnessassistant.viewmodel.GoalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalsSettingsScreen(goalsViewModel: GoalsViewModel = viewModel()) {
    // Get current goal values as strings for input
    var targetSteps by remember { mutableStateOf(goalsViewModel.goals.steps.toString()) }
    var targetSleep by remember { mutableStateOf(goalsViewModel.goals.sleepHours.toString()) }
    var targetCalories by remember { mutableStateOf(goalsViewModel.goals.calories.toString()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Set Personal Goals") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = targetSteps,
                    onValueChange = { targetSteps = it },
                    label = { Text("Steps per day") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = targetSleep,
                    onValueChange = { targetSleep = it },
                    label = { Text("Sleep hours per day") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = targetCalories,
                    onValueChange = { targetCalories = it },
                    label = { Text("Calories per day") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Update the goals into the GoalsViewModel.
                        goalsViewModel.goals = Goals(
                            steps = targetSteps.toIntOrNull() ?: 10000,
                            sleepHours = targetSleep.toFloatOrNull() ?: 8f,
                            calories = targetCalories.toIntOrNull() ?: 2200
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Goals")
                }
            }
        }
    )
}

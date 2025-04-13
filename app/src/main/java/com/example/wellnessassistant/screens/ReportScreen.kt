package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessassistant.viewmodel.DietViewModel
import com.example.wellnessassistant.viewmodel.FitnessViewModel
import com.example.wellnessassistant.viewmodel.MentalHealthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    fitnessViewModel: FitnessViewModel = viewModel(),
    dietViewModel: DietViewModel = viewModel(),
    mentalHealthViewModel: MentalHealthViewModel = viewModel()
) {
    // Sample weekly data for charts
    val caloriesData = listOf(
        "Mon" to 2200f,
        "Tue" to 2000f,
        "Wed" to 2300f,
        "Thu" to 2100f,
        "Fri" to 2400f,
        "Sat" to 2000f,
        "Sun" to 1900f
    )
    val workoutData = listOf(
        "Mon" to 45f,
        "Tue" to 30f,
        "Wed" to 50f,
        "Thu" to 40f,
        "Fri" to 60f,
        "Sat" to 20f,
        "Sun" to 0f
    )
    val sleepData = listOf(
        "Mon" to 7.5f,
        "Tue" to 6.5f,
        "Wed" to 8f,
        "Thu" to 7f,
        "Fri" to 6.5f,
        "Sat" to 8f,
        "Sun" to 7f
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Summary Report") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Calories Consumption Chart", style = MaterialTheme.typography.titleMedium)
            SimpleBarChart(data = caloriesData, maxValue = 2500f, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Text("Workout Activity Chart (minutes)", style = MaterialTheme.typography.titleMedium)
            SimpleBarChart(data = workoutData, maxValue = 60f, modifier = Modifier.fillMaxWidth(), barColor = MaterialTheme.colorScheme.secondary)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Average Sleep Duration Chart", style = MaterialTheme.typography.titleMedium)
            SimpleBarChart(data = sleepData, maxValue = 10f, modifier = Modifier.fillMaxWidth(), barColor = MaterialTheme.colorScheme.tertiary)
        }
    }
}

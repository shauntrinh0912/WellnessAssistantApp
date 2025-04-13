package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wellnessassistant.viewmodel.DietViewModel
import com.example.wellnessassistant.viewmodel.FitnessViewModel
import com.example.wellnessassistant.viewmodel.GoalsViewModel
import com.example.wellnessassistant.viewmodel.MentalHealthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    fitnessViewModel: FitnessViewModel = viewModel(),
    dietViewModel: DietViewModel = viewModel(),
    mentalHealthViewModel: MentalHealthViewModel = viewModel(),
    goalsViewModel: GoalsViewModel = viewModel()
) {
    val currentSteps = 7500
    val currentCalories = dietViewModel.totalCalories()
    val currentSleep = mentalHealthViewModel.totalSleepHours()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All-in-one Dashboard") }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text("Hello John!", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Your activity summary today:", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(13.dp))

                // Physical Activity Summary
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(13.dp)) {
                        Text(
                            "Physical Activity",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Total duration: ${fitnessViewModel.totalDuration()} minutes")
                        Text("Calories burned: ${fitnessViewModel.totalCaloriesBurned()} kcal")
                    }
                }

                // Diet Summary
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(13.dp)) {
                        Text(
                            "Diet",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Total calories consumed: ${dietViewModel.totalCalories()} kcal")
                        Text("Number of meals: ${dietViewModel.meals.size}")
                    }
                }

                // Mental Health & Sleep Summary
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(13.dp)) {
                        Text(
                            "Mental Health & Sleep",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Number of entries: ${mentalHealthViewModel.totalEntries()}")
                        if (mentalHealthViewModel.entries.isNotEmpty()) {
                            val latestEntry = mentalHealthViewModel.entries.last()
                            Text("Recent: ${latestEntry.mood} - ${latestEntry.note}")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Total sleep hours: $currentSleep hours")
                    }
                }

                Spacer(modifier = Modifier.height(13.dp))

                GoalsSummary(
                    goals = goalsViewModel.goals,
                    currentSteps = currentSteps,
                    currentSleep = currentSleep,
                    currentCalories = currentCalories
                )

                Spacer(modifier = Modifier.height(13.dp))

                Button(
                    onClick = { navController.navigate("goals") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Set Personal Goals")
                }

                Spacer(modifier = Modifier.height(13.dp))

                Button(
                    onClick = { navController.navigate("report") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("View Reports & Charts")
                }
            }
        }
    )
}

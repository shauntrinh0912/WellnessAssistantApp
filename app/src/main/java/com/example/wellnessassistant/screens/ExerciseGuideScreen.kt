package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Model cho Exercise
data class Exercise(
    val title: String,
    val description: String
)

// Danh sách các bài tập với hướng dẫn bằng tiếng Anh
val sampleExercises = listOf(
    Exercise(
        title = "Push-ups",
        description = "Focus on your chest, shoulders, and triceps. Perform 3 sets of 12-15 repetitions."
    ),
    Exercise(
        title = "Squats",
        description = "Work your quadriceps, hamstrings, and glutes. Perform 3 sets of 15-20 repetitions."
    ),
    Exercise(
        title = "Plank",
        description = "Strengthen your core muscles. Hold this position for 30-60 seconds."
    ),
    Exercise(
        title = "Burpees",
        description = "Combine squats, push-ups, and jumps for a full-body workout. Perform 3 sets of 10-12 repetitions."
    ),
    Exercise(
        title = "Lunges",
        description = "Improve your leg strength and balance. Perform 3 sets of 10 repetitions per leg."
    ),
    Exercise(
        title = "Mountain Climbers",
        description = "Boost your cardio endurance and core stability. Perform 3 sets of 30 seconds."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseGuideScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Exercise Guide") })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(sampleExercises) { exercise ->
                    ExerciseItem(exercise = exercise)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exercise.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = exercise.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

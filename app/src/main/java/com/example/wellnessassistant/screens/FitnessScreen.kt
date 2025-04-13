package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wellnessassistant.viewmodel.ExerciseSession
import com.example.wellnessassistant.viewmodel.FitnessViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessScreen(
    navController: NavController,
    fitnessViewModel: FitnessViewModel = viewModel()
) {
    var showAddSessionDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fitness - Track Physical Activity") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddSessionDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Workout Session")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Total workout time: ${fitnessViewModel.totalDuration()} minutes",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Total calories burned: ${fitnessViewModel.totalCaloriesBurned()} kcal",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(fitnessViewModel.sessions) { session ->
                        ExerciseSessionItem(session = session)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("bmi") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Calculate BMI")
                    }
                    Button(
                        onClick = { navController.navigate("exerciseGuide") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Exercise Guide")
                    }
                }
            }
        }
    )

    if (showAddSessionDialog) {
        AddSessionDialog(
            onDismiss = { showAddSessionDialog = false },
            onAddSession = { session ->
                fitnessViewModel.addSession(session)
                showAddSessionDialog = false
            }
        )
    }
}

@Composable
fun ExerciseSessionItem(session: ExerciseSession) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = session.type, style = MaterialTheme.typography.titleMedium)
            Text(text = "Duration: ${session.duration} minutes")
            Text(text = "Calories burned: ${session.caloriesBurned} kcal")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSessionDialog(
    onDismiss: () -> Unit,
    onAddSession: (ExerciseSession) -> Unit
) {
    var type by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var caloriesBurned by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val newSession = ExerciseSession(
                    type = type,
                    duration = duration.toIntOrNull() ?: 0,
                    caloriesBurned = caloriesBurned.toIntOrNull() ?: 0
                )
                onAddSession(newSession)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Add Workout Session") },
        text = {
            Column {
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Exercise Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration (minutes)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = caloriesBurned,
                    onValueChange = { caloriesBurned = it },
                    label = { Text("Calories Burned (kcal)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

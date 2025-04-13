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
import com.example.wellnessassistant.viewmodel.DietViewModel
import com.example.wellnessassistant.viewmodel.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietScreen(
    navController: NavController? = null,
    dietViewModel: DietViewModel = viewModel()
) {
    var showAddMealDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Diet - Manage your meals") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddMealDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Meal")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Display summary info
                Text(
                    text = "Total calories consumed: ${dietViewModel.totalCalories()} kcal",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Number of meals: ${dietViewModel.meals.size}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                // List of meals
                LazyColumn {
                    items(dietViewModel.meals) { meal ->
                        MealItem(meal = meal)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Button to view recipes
                Button(
                    onClick = { navController?.navigate("recipeGuide") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("View Recipes and Cooking")
                }
            }
        }
    )

    if (showAddMealDialog) {
        AddMealDialog(
            onDismiss = { showAddMealDialog = false },
            onAddMeal = { meal ->
                dietViewModel.addMeal(meal)
                showAddMealDialog = false
            }
        )
    }
}

@Composable
fun MealItem(meal: Meal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = meal.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Calories: ${meal.calories} kcal")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealDialog(
    onDismiss: () -> Unit,
    onAddMeal: (Meal) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val newMeal = Meal(
                    name = name,
                    calories = calories.toIntOrNull() ?: 0,
                )
                onAddMeal(newMeal)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Add New Meal") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Meal Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories (kcal)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

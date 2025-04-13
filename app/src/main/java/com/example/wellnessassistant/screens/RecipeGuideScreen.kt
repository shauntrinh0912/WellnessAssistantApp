package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Define model for Recipe
data class Recipe(
    val title: String,
    val description: String
)

// Sample recipe list with English names and descriptions
val sampleRecipes = listOf(
    Recipe(
        title = "Traditional Beef Pho",
        description = "Ingredients include beef, rice noodles, and a flavorful broth infused with spices and herbs."
    ),
    Recipe(
        title = "Hanoi Grilled Pork with Vermicelli",
        description = "Fresh vermicelli noodles with grilled pork, herbs, and a sweet-savory fish sauce dip."
    ),
    Recipe(
        title = "Broken Rice with Grilled Pork",
        description = "Famous dish with grilled pork chops, shredded pork skin, fried egg, and sweet fish sauce."
    ),
    Recipe(
        title = "Fresh Spring Rolls",
        description = "Rice paper rolls with shrimp, pork, herbs, and rice vermicelli. Best served with dipping sauce."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeGuideScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Recipe Guide & Cooking Instructions") })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(sampleRecipes) { recipe ->
                    RecipeItem(recipe = recipe)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

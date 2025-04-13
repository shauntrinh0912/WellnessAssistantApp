package com.example.wellnessassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wellnessassistant.screens.BMIScreen
import com.example.wellnessassistant.screens.DashboardScreen
import com.example.wellnessassistant.screens.DietScreen
import com.example.wellnessassistant.screens.FitnessScreen
import com.example.wellnessassistant.screens.GoalsSettingsScreen
import com.example.wellnessassistant.screens.MentalHealthScreen
import com.example.wellnessassistant.screens.RecipeGuideScreen
import com.example.wellnessassistant.screens.ReportScreen
import com.example.wellnessassistant.ui.theme.WellnessAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellnessAssistantTheme {
                AppScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("dashboard") },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("fitness") },
                    icon = { Icon(imageVector = Icons.Default.FitnessCenter, contentDescription = "Fitness") },
                    label = { Text("Fitness") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("diet") },
                    icon = { Icon(imageVector = Icons.Default.Restaurant, contentDescription = "Diet") },
                    label = { Text("Diet") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("mentalHealth") },
                    icon = { Icon(imageVector = Icons.Default.Mood, contentDescription = "Mental Health") },
                    label = { Text("Mental Health") }
                )
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("dashboard") { DashboardScreen(navController = navController) }
                composable("fitness") { FitnessScreen(navController = navController) }
                composable("diet") { DietScreen(navController = navController) }
                composable("mentalHealth") { MentalHealthScreen() }
                composable("goals") { GoalsSettingsScreen() }
                composable("report") { ReportScreen() }
                composable("recipeGuide") { RecipeGuideScreen() }
                composable("bmi") { BMIScreen() }
            }
        }
    )
}

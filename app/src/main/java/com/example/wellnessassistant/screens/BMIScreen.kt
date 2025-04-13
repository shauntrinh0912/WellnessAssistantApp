package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BMI Calculator") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            BMICalculator()
        }
    }
}

@Composable
fun BMICalculator() {
    var weightInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    var bmiStatus by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Enter weight and height (cm) to calculate BMI", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                bmiResult = calculateBMI(weightInput, heightInput)
                bmiResult?.let {
                    bmiStatus = getBMIStatus(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate BMI")
        }
        Spacer(modifier = Modifier.height(8.dp))
        bmiResult?.let { result ->
            Text(
                text = "Your BMI: %.2f".format(result),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Evaluate: $bmiStatus",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// BMI calculator with height entered in cm (convert to meters before calculating)
private fun calculateBMI(weight: String, height: String): Double? {
    val weightValue = weight.toDoubleOrNull()
    val heightValueCm = height.toDoubleOrNull()

    if (weightValue == null || heightValueCm == null || heightValueCm <= 0) {
        return null
    }
    val heightValueM = heightValueCm / 100.0 // convert from cm to m
    return weightValue / (heightValueM * heightValueM)
}

// BMI evaluation function
private fun getBMIStatus(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal"
        bmi < 30.0 -> "Overweight"
        else -> "Obesity"
    }
}

package com.example.wellnessassistant.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessassistant.viewmodel.MentalHealthEntry
import com.example.wellnessassistant.viewmodel.MentalHealthViewModel
import com.example.wellnessassistant.viewmodel.SleepEntry
import java.time.LocalDate.now

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentalHealthScreen(mentalHealthViewModel: MentalHealthViewModel = viewModel()) {
    val mentalEntries = mentalHealthViewModel.entries
    val sleepEntries = mentalHealthViewModel.sleepEntries

    var showMentalEntryDialog by remember { mutableStateOf(false) }
    var showSleepEntryDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = { showMentalEntryDialog = true }) {
                    Text("MH")
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(onClick = { showSleepEntryDialog = true }) {
                    Text("SL")
                }
            }
        },
        topBar = {
            TopAppBar(title = { Text("Mental Health & Sleep Tracker") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Mental health section
            Text(
                text = "Total entries: ${mentalHealthViewModel.totalEntries()}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            if (mentalEntries.isNotEmpty()) {
                val latestEntry = mentalEntries.last()
                Text("Recent: ${latestEntry.mood} - ${latestEntry.note}")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text("Mood log entries:", style = MaterialTheme.typography.titleSmall)
            LazyColumn {
                items(mentalEntries) { entry ->
                    MentalHealthEntryItem(entry = entry)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sleep section
            Text(
                text = "Total sleep hours: ${mentalHealthViewModel.totalSleepHours()} hours",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sleep records:", style = MaterialTheme.typography.titleSmall)
            LazyColumn {
                items(sleepEntries) { sleepEntry ->
                    SleepEntryItem(sleepEntry = sleepEntry)
                }
            }
        }
    }

    if (showMentalEntryDialog) {
        AddMentalHealthEntryDialog(
            onDismiss = { showMentalEntryDialog = false },
            onAddEntry = { entry ->
                mentalHealthViewModel.addEntry(entry)
                showMentalEntryDialog = false
            }
        )
    }

    if (showSleepEntryDialog) {
        AddSleepEntryDialog(
            onDismiss = { showSleepEntryDialog = false },
            onAddEntry = { entry ->
                mentalHealthViewModel.addSleepEntry(entry)
                showSleepEntryDialog = false
            }
        )
    }
}

@Composable
fun MentalHealthEntryItem(entry: MentalHealthEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Mood: ${entry.mood}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Note: ${entry.note}")
            Text(text = "Date: ${entry.date}")
        }
    }
}

@Composable
fun SleepEntryItem(sleepEntry: SleepEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Sleep Duration: ${sleepEntry.duration} hours", style = MaterialTheme.typography.titleMedium)
            Text(text = "Quality: ${sleepEntry.quality}")
            Text(text = "Date: ${sleepEntry.date}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepEntryDialog(
    onDismiss: () -> Unit,
    onAddEntry: (SleepEntry) -> Unit
) {
    var durationInput by remember { mutableStateOf("") }
    var quality by remember { mutableStateOf("") }
    val currentDate = now().toString()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val duration = durationInput.toFloatOrNull() ?: 0f
                val newEntry = SleepEntry(
                    duration = duration,
                    quality = quality,
                    date = currentDate
                )
                onAddEntry(newEntry)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Add Sleep Entry") },
        text = {
            Column {
                OutlinedTextField(
                    value = durationInput,
                    onValueChange = { durationInput = it },
                    label = { Text("Sleep Duration (hours)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = quality,
                    onValueChange = { quality = it },
                    label = { Text("Sleep Quality (e.g. Good, Average, Poor)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMentalHealthEntryDialog(
    onDismiss: () -> Unit,
    onAddEntry: (MentalHealthEntry) -> Unit
) {
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val currentDate = now().toString()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val newEntry = MentalHealthEntry(
                    mood = mood,
                    note = note,
                    date = currentDate
                )
                onAddEntry(newEntry)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Add Mood Entry") },
        text = {
            Column {
                OutlinedTextField(
                    value = mood,
                    onValueChange = { mood = it },
                    label = { Text("Mood (e.g. Happy, Sad, Stressed)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

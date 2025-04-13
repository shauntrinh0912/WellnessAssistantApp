package com.example.wellnessassistant.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class MentalHealthEntry(
    val mood: String,
    val note: String,
    val date: String
)

// Data model cho Sleep Tracker
data class SleepEntry(
    val duration: Float,
    val quality: String,
    val date: String
)

class MentalHealthViewModel : ViewModel() {

    val entries = mutableStateListOf<MentalHealthEntry>()

    val sleepEntries = mutableStateListOf<SleepEntry>()

    fun addEntry(entry: MentalHealthEntry) {
        entries.add(entry)
    }

    fun totalEntries(): Int = entries.size

    fun addSleepEntry(entry: SleepEntry) {
        sleepEntries.add(entry)
    }

    fun totalSleepHours(): Float { return sleepEntries.map { it.duration }.sum() }
}

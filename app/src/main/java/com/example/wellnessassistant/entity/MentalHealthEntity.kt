package com.example.wellnessassistant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mental_health_entries")
data class MentalHealthEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mood: String,
    val note: String,
    val date: String
)
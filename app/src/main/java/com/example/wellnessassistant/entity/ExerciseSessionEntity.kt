package com.example.wellnessassistant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_sessions")
data class ExerciseSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val duration: Int,        // thời gian tập (phút)
    val caloriesBurned: Int   // calo đốt cháy (kcal)
)

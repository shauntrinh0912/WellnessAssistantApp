package com.example.wellnessassistant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseSessionDao {
    @Insert
    suspend fun insertSession(session: ExerciseSessionEntity): Long

    @Query("SELECT * FROM exercise_sessions")
    fun getAllSessions(): Flow<List<ExerciseSessionEntity>>
}
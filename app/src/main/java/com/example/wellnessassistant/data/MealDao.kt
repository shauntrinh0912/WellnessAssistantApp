package com.example.wellnessassistant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.wellnessassistant.MealEntity


@Dao
interface MealDao {
    @Insert
    suspend fun insertMeal(meal: MealEntity): Long

    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<MealEntity>>
}

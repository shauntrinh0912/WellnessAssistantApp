package com.example.wellnessassistant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MentalHealthDao {
    // Hàm chèn một entry mới, trả về ID (Long)
    @Insert
    suspend fun insertEntry(entry: MentalHealthEntity): Long

    // Lấy danh sách các entry được lưu trữ dưới dạng Flow
    @Query("SELECT * FROM mental_health_entries")
    fun getAllEntries(): Flow<List<MentalHealthEntity>>
}
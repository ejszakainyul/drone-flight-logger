package com.dronelogger.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dronelogger.app.data.model.DroneInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface DroneInfoDao {
    @Insert
    suspend fun insert(droneInfo: DroneInfo): Long

    @Update
    suspend fun update(droneInfo: DroneInfo)

    @Delete
    suspend fun delete(droneInfo: DroneInfo)

    @Query("SELECT * FROM drone_info WHERE id = :id")
    suspend fun getById(id: Long): DroneInfo?

    @Query("SELECT * FROM drone_info ORDER BY name ASC")
    fun getAllDrones(): Flow<List<DroneInfo>>

    @Query("SELECT COUNT(*) FROM drone_info")
    fun getDroneCount(): Flow<Int>
}

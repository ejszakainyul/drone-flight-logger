package com.dronelogger.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dronelogger.app.data.model.FlightLog
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightLogDao {
    @Insert
    suspend fun insert(flightLog: FlightLog): Long

    @Update
    suspend fun update(flightLog: FlightLog)

    @Delete
    suspend fun delete(flightLog: FlightLog)

    @Query("SELECT * FROM flight_logs WHERE id = :id")
    suspend fun getById(id: Long): FlightLog?

    @Query("SELECT * FROM flight_logs ORDER BY start_time DESC")
    fun getAllFlights(): Flow<List<FlightLog>>

    @Query("SELECT * FROM flight_logs WHERE drone_id = :droneId ORDER BY start_time DESC")
    fun getFlightsByDrone(droneId: Long): Flow<List<FlightLog>>

    @Query("SELECT * FROM flight_logs WHERE start_time >= datetime(:startDate) AND start_time <= datetime(:endDate) ORDER BY start_time DESC")
    fun getFlightsByDateRange(startDate: String, endDate: String): Flow<List<FlightLog>>

    @Query("SELECT COUNT(*) FROM flight_logs")
    fun getTotalFlightsCount(): Flow<Int>

    @Query("SELECT SUM(duration_minutes) FROM flight_logs")
    fun getTotalFlightTime(): Flow<Int>

    @Query("SELECT AVG(max_altitude) FROM flight_logs")
    fun getAverageMaxAltitude(): Flow<Double>

    @Query("SELECT AVG(max_speed) FROM flight_logs")
    fun getAverageMaxSpeed(): Flow<Double>

    @Query("DELETE FROM flight_logs WHERE id = :id")
    suspend fun deleteById(id: Long)
}

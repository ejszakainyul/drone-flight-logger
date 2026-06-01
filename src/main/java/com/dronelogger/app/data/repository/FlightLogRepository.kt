package com.dronelogger.app.data.repository

import com.dronelogger.app.data.database.FlightLogDao
import com.dronelogger.app.data.model.FlightLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlightLogRepository @Inject constructor(
    private val flightLogDao: FlightLogDao
) {
    fun getAllFlights(): Flow<List<FlightLog>> = flightLogDao.getAllFlights()

    fun getFlightsByDrone(droneId: Long): Flow<List<FlightLog>> =
        flightLogDao.getFlightsByDrone(droneId)

    fun getFlightsByDateRange(startDate: String, endDate: String): Flow<List<FlightLog>> =
        flightLogDao.getFlightsByDateRange(startDate, endDate)

    fun getTotalFlightsCount(): Flow<Int> = flightLogDao.getTotalFlightsCount()

    fun getTotalFlightTime(): Flow<Int> = flightLogDao.getTotalFlightTime()

    fun getAverageMaxAltitude(): Flow<Double> = flightLogDao.getAverageMaxAltitude()

    fun getAverageMaxSpeed(): Flow<Double> = flightLogDao.getAverageMaxSpeed()

    suspend fun insertFlight(flightLog: FlightLog): Long =
        flightLogDao.insert(flightLog)

    suspend fun updateFlight(flightLog: FlightLog) =
        flightLogDao.update(flightLog)

    suspend fun deleteFlight(flightLog: FlightLog) =
        flightLogDao.delete(flightLog)

    suspend fun deleteFlightById(id: Long) =
        flightLogDao.deleteById(id)

    suspend fun getFlightById(id: Long): FlightLog? =
        flightLogDao.getById(id)
}

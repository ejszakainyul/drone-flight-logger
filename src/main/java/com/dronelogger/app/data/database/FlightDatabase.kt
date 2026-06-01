package com.dronelogger.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dronelogger.app.data.model.FlightLog
import com.dronelogger.app.data.model.DroneInfo

@Database(
    entities = [FlightLog::class, DroneInfo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun flightLogDao(): FlightLogDao
    abstract fun droneInfoDao(): DroneInfoDao

    companion object {
        const val DATABASE_NAME = "drone_flight_logger.db"
    }
}

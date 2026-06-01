package com.dronelogger.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "flight_logs",
    foreignKeys = [
        ForeignKey(
            entity = DroneInfo::class,
            parentColumns = ["id"],
            childColumns = ["drone_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FlightLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo("drone_id")
    val droneId: Long,

    @ColumnInfo("start_time")
    val startTime: LocalDateTime,

    @ColumnInfo("end_time")
    val endTime: LocalDateTime? = null,

    @ColumnInfo("duration_minutes")
    val durationMinutes: Int = 0,

    @ColumnInfo("max_altitude")
    val maxAltitude: Double = 0.0, // meters

    @ColumnInfo("max_speed")
    val maxSpeed: Double = 0.0, // km/h

    @ColumnInfo("distance_traveled")
    val distanceTraveled: Double = 0.0, // kilometers

    @ColumnInfo("battery_start")
    val batteryStart: Int = 100, // percentage

    @ColumnInfo("battery_end")
    val batteryEnd: Int = 0, // percentage

    @ColumnInfo("gps_signal")
    val gpsSignal: Int = 0, // 0-4

    @ColumnInfo("temperature")
    val temperature: Double = 0.0, // celsius

    @ColumnInfo("location_latitude")
    val locationLatitude: Double = 0.0,

    @ColumnInfo("location_longitude")
    val locationLongitude: Double = 0.0,

    @ColumnInfo("location_name")
    val locationName: String = "",

    @ColumnInfo("notes")
    val notes: String = "",

    @ColumnInfo("weather_condition")
    val weatherCondition: String = "", // sunny, rainy, cloudy, etc.

    @ColumnInfo("wind_speed")
    val windSpeed: Double = 0.0 // km/h
)

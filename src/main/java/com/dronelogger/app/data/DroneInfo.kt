package com.dronelogger.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "drone_info")
data class DroneInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("model")
    val model: String,

    @ColumnInfo("manufacturer")
    val manufacturer: String = "",

    @ColumnInfo("serial_number")
    val serialNumber: String = "",

    @ColumnInfo("color")
    val color: String = "",

    @ColumnInfo("purchase_date")
    val purchaseDate: LocalDateTime? = null,

    @ColumnInfo("max_altitude")
    val maxAltitude: Double = 0.0, // meters

    @ColumnInfo("max_speed")
    val maxSpeed: Double = 0.0, // km/h

    @ColumnInfo("battery_capacity")
    val batteryCapacity: Int = 0, // mAh

    @ColumnInfo("weight")
    val weight: Double = 0.0, // grams

    @ColumnInfo("notes")
    val notes: String = ""
)

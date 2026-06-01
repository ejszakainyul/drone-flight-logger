package com.dronelogger.app.di

import android.content.Context
import androidx.room.Room
import com.dronelogger.app.data.database.FlightDatabase
import com.dronelogger.app.data.database.FlightLogDao
import com.dronelogger.app.data.database.DroneInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFlightDatabase(
        @ApplicationContext context: Context
    ): FlightDatabase {
        return Room.databaseBuilder(
            context,
            FlightDatabase::class.java,
            FlightDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideFlightLogDao(database: FlightDatabase): FlightLogDao {
        return database.flightLogDao()
    }

    @Singleton
    @Provides
    fun provideDroneInfoDao(database: FlightDatabase): DroneInfoDao {
        return database.droneInfoDao()
    }
}

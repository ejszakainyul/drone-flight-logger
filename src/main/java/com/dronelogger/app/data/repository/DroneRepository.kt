package com.dronelogger.app.data.repository

import com.dronelogger.app.data.database.DroneInfoDao
import com.dronelogger.app.data.model.DroneInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DroneRepository @Inject constructor(
    private val droneInfoDao: DroneInfoDao
) {
    fun getAllDrones(): Flow<List<DroneInfo>> = droneInfoDao.getAllDrones()

    fun getDroneCount(): Flow<Int> = droneInfoDao.getDroneCount()

    suspend fun insertDrone(droneInfo: DroneInfo): Long =
        droneInfoDao.insert(droneInfo)

    suspend fun updateDrone(droneInfo: DroneInfo) =
        droneInfoDao.update(droneInfo)

    suspend fun deleteDrone(droneInfo: DroneInfo) =
        droneInfoDao.delete(droneInfo)

    suspend fun getDroneById(id: Long): DroneInfo? =
        droneInfoDao.getById(id)
}

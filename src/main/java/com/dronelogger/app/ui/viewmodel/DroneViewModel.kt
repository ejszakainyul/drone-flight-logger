package com.dronelogger.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dronelogger.app.data.model.DroneInfo
import com.dronelogger.app.data.repository.DroneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DroneViewModel @Inject constructor(
    private val droneRepository: DroneRepository
) : ViewModel() {

    private val _allDrones = MutableStateFlow<List<DroneInfo>>(emptyList())
    val allDrones: StateFlow<List<DroneInfo>> = _allDrones.asStateFlow()

    private val _selectedDrone = MutableStateFlow<DroneInfo?>(null)
    val selectedDrone: StateFlow<DroneInfo?> = _selectedDrone.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAllDrones()
    }

    private fun loadAllDrones() {
        viewModelScope.launch {
            droneRepository.getAllDrones().collect { drones ->
                _allDrones.value = drones
            }
        }
    }

    fun addDrone(droneInfo: DroneInfo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                droneRepository.insertDrone(droneInfo)
                loadAllDrones()
            } catch (e: Exception) {
                _errorMessage.value = "Error adding drone: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateDrone(droneInfo: DroneInfo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                droneRepository.updateDrone(droneInfo)
                loadAllDrones()
            } catch (e: Exception) {
                _errorMessage.value = "Error updating drone: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteDrone(droneInfo: DroneInfo) {
        viewModelScope.launch {
            try {
                droneRepository.deleteDrone(droneInfo)
                loadAllDrones()
            } catch (e: Exception) {
                _errorMessage.value = "Error deleting drone: ${e.message}"
            }
        }
    }

    fun selectDrone(droneInfo: DroneInfo) {
        _selectedDrone.value = droneInfo
    }

    fun clearSelection() {
        _selectedDrone.value = null
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

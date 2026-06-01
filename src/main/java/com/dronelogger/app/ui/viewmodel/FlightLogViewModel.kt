package com.dronelogger.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dronelogger.app.data.model.FlightLog
import com.dronelogger.app.data.repository.FlightLogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightLogViewModel @Inject constructor(
    private val flightLogRepository: FlightLogRepository
) : ViewModel() {

    private val _allFlights = MutableStateFlow<List<FlightLog>>(emptyList())
    val allFlights: StateFlow<List<FlightLog>> = _allFlights.asStateFlow()

    private val _totalFlights = MutableStateFlow(0)
    val totalFlights: StateFlow<Int> = _totalFlights.asStateFlow()

    private val _totalFlightTime = MutableStateFlow(0)
    val totalFlightTime: StateFlow<Int> = _totalFlightTime.asStateFlow()

    private val _averageAltitude = MutableStateFlow(0.0)
    val averageAltitude: StateFlow<Double> = _averageAltitude.asStateFlow()

    private val _averageSpeed = MutableStateFlow(0.0)
    val averageSpeed: StateFlow<Double> = _averageSpeed.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAllFlights()
        loadStatistics()
    }

    private fun loadAllFlights() {
        viewModelScope.launch {
            flightLogRepository.getAllFlights().collect { flights ->
                _allFlights.value = flights
            }
        }
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            flightLogRepository.getTotalFlightsCount().collect { count ->
                _totalFlights.value = count
            }
        }

        viewModelScope.launch {
            flightLogRepository.getTotalFlightTime().collect { time ->
                _totalFlightTime.value = time
            }
        }

        viewModelScope.launch {
            flightLogRepository.getAverageMaxAltitude().collect { altitude ->
                _averageAltitude.value = altitude
            }
        }

        viewModelScope.launch {
            flightLogRepository.getAverageMaxSpeed().collect { speed ->
                _averageSpeed.value = speed
            }
        }
    }

    fun addFlight(flightLog: FlightLog) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                flightLogRepository.insertFlight(flightLog)
                loadAllFlights()
                loadStatistics()
            } catch (e: Exception) {
                _errorMessage.value = "Error adding flight: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateFlight(flightLog: FlightLog) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                flightLogRepository.updateFlight(flightLog)
                loadAllFlights()
            } catch (e: Exception) {
                _errorMessage.value = "Error updating flight: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteFlight(flightLog: FlightLog) {
        viewModelScope.launch {
            try {
                flightLogRepository.deleteFlight(flightLog)
                loadAllFlights()
                loadStatistics()
            } catch (e: Exception) {
                _errorMessage.value = "Error deleting flight: ${e.message}"
            }
        }
    }

    fun getFlightsByDrone(droneId: Long) {
        viewModelScope.launch {
            flightLogRepository.getFlightsByDrone(droneId).collect { flights ->
                _allFlights.value = flights
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

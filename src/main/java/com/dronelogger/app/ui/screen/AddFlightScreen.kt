package com.dronelogger.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dronelogger.app.data.model.FlightLog
import com.dronelogger.app.ui.viewmodel.DroneViewModel
import com.dronelogger.app.ui.viewmodel.FlightLogViewModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlightScreen(
    flightViewModel: FlightLogViewModel = hiltViewModel(),
    droneViewModel: DroneViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val allDrones by droneViewModel.allDrones.collectAsState()

    var selectedDroneId by remember { mutableStateOf<Long?>(null) }
    var maxAltitude by remember { mutableStateOf("") }
    var maxSpeed by remember { mutableStateOf("") }
    var durationMinutes by remember { mutableStateOf("") }
    var batteryStart by remember { mutableStateOf("") }
    var batteryEnd by remember { mutableStateOf("") }
    var locationName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var weatherCondition by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Flight Log") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Select Drone",
                    style = MaterialTheme.typography.labelLarge
                )
                if (allDrones.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        allDrones.forEach { drone ->
                            Button(
                                onClick = { selectedDroneId = drone.id },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(drone.name)
                            }
                        }
                    }
                } else {
                    Text("No drones available. Add a drone first.")
                }
            }

            item {
                OutlinedTextField(
                    value = durationMinutes,
                    onValueChange = { durationMinutes = it },
                    label = { Text("Duration (minutes)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = maxAltitude,
                    onValueChange = { maxAltitude = it },
                    label = { Text("Max Altitude (meters)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = maxSpeed,
                    onValueChange = { maxSpeed = it },
                    label = { Text("Max Speed (km/h)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = batteryStart,
                    onValueChange = { batteryStart = it },
                    label = { Text("Battery Start (%)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = batteryEnd,
                    onValueChange = { batteryEnd = it },
                    label = { Text("Battery End (%)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = locationName,
                    onValueChange = { locationName = it },
                    label = { Text("Location Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = weatherCondition,
                    onValueChange = { weatherCondition = it },
                    label = { Text("Weather Condition") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }

            item {
                Button(
                    onClick = {
                        if (selectedDroneId != null && durationMinutes.isNotEmpty()) {
                            val flightLog = FlightLog(
                                droneId = selectedDroneId!!,
                                startTime = LocalDateTime.now(),
                                durationMinutes = durationMinutes.toIntOrNull() ?: 0,
                                maxAltitude = maxAltitude.toDoubleOrNull() ?: 0.0,
                                maxSpeed = maxSpeed.toDoubleOrNull() ?: 0.0,
                                batteryStart = batteryStart.toIntOrNull() ?: 100,
                                batteryEnd = batteryEnd.toIntOrNull() ?: 0,
                                locationName = locationName,
                                notes = notes,
                                weatherCondition = weatherCondition
                            )
                            flightViewModel.addFlight(flightLog)
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Flight")
                }
            }
        }
    }
}

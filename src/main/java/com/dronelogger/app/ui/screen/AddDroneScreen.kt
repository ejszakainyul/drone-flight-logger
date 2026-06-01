package com.dronelogger.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dronelogger.app.data.model.DroneInfo
import com.dronelogger.app.ui.viewmodel.DroneViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDroneScreen(
    viewModel: DroneViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var serialNumber by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var maxAltitude by remember { mutableStateOf("") }
    var maxSpeed by remember { mutableStateOf("") }
    var batteryCapacity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Drone") },
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
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Drone Name *") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Model *") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = manufacturer,
                    onValueChange = { manufacturer = it },
                    label = { Text("Manufacturer") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = serialNumber,
                    onValueChange = { serialNumber = it },
                    label = { Text("Serial Number") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color") },
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
                    value = batteryCapacity,
                    onValueChange = { batteryCapacity = it },
                    label = { Text("Battery Capacity (mAh)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (grams)") },
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
                        if (name.isNotEmpty() && model.isNotEmpty()) {
                            val droneInfo = DroneInfo(
                                name = name,
                                model = model,
                                manufacturer = manufacturer,
                                serialNumber = serialNumber,
                                color = color,
                                maxAltitude = maxAltitude.toDoubleOrNull() ?: 0.0,
                                maxSpeed = maxSpeed.toDoubleOrNull() ?: 0.0,
                                batteryCapacity = batteryCapacity.toIntOrNull() ?: 0,
                                weight = weight.toDoubleOrNull() ?: 0.0,
                                notes = notes
                            )
                            viewModel.addDrone(droneInfo)
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Drone")
                }
            }
        }
    }
}

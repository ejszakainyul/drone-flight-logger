package com.dronelogger.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dronelogger.app.ui.viewmodel.DroneViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DroneScreen(
    viewModel: DroneViewModel = hiltViewModel(),
    onAddDroneClick: () -> Unit
) {
    val allDrones by viewModel.allDrones.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Drones") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDroneClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Drone")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (allDrones.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No drones yet. Add your first drone!",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                items(allDrones.size) { index ->
                    DroneCard(
                        drone = allDrones[index],
                        onDelete = { viewModel.deleteDrone(allDrones[index]) }
                    )
                }
            }
        }
    }
}

@Composable
fun DroneCard(
    drone: com.dronelogger.app.data.model.DroneInfo,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .align(Alignment.CenterStart)
                ) {
                    Text(
                        drone.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        drone.model,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            if (drone.manufacturer.isNotEmpty()) {
                Text("Manufacturer: ${drone.manufacturer}", style = MaterialTheme.typography.bodySmall)
            }

            if (drone.serialNumber.isNotEmpty()) {
                Text("Serial: ${drone.serialNumber}", style = MaterialTheme.typography.bodySmall)
            }

            if (drone.maxSpeed > 0) {
                Text("Max Speed: ${drone.maxSpeed} km/h", style = MaterialTheme.typography.bodySmall)
            }

            if (drone.maxAltitude > 0) {
                Text("Max Altitude: ${drone.maxAltitude} m", style = MaterialTheme.typography.bodySmall)
            }

            if (drone.weight > 0) {
                Text("Weight: ${drone.weight} g", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

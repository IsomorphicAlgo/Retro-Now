package com.retronow.app.ui.planetdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.retronow.app.data.database.DatabaseProvider
import com.retronow.app.domain.model.Planet
import com.retronow.app.ui.theme.ActiveRetrograde
import com.retronow.app.ui.theme.InactiveRetrograde
import com.retronow.app.ui.theme.RetrogradeBlue
import com.retronow.app.ui.theme.RetrogradeGreen
import com.retronow.app.ui.theme.RetrogradePurple
import com.retronow.app.utils.RetrogradeCalculator
import java.time.format.DateTimeFormatter

/**
 * Planet detail screen showing information about a selected planet
 */
@Composable
fun PlanetDetailScreen(
    planetId: String?,
    onBackClick: () -> Unit,
    viewModel: PlanetDetailViewModel = viewModel(
        factory = PlanetDetailViewModelFactory(
            RetrogradeCalculator(
                DatabaseProvider.getRepository(LocalContext.current)
            ),
            DatabaseProvider.getRepository(LocalContext.current)
        )
    )
) {
    val uiState by viewModel.uiState.collectAsState()
    var showPlanetSelector by remember { mutableStateOf(false) }
    
    // Load planet detail on first composition
    if (planetId != null && uiState.selectedPlanetId != planetId) {
        viewModel.loadPlanetDetail(planetId)
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            RetrogradeBlue.copy(alpha = 0.8f),
                            RetrogradePurple.copy(alpha = 0.8f),
                            RetrogradeGreen.copy(alpha = 0.8f)
                        )
                    )
                )
        )
        
        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar
            TopAppBar(
                title = {
                    Text(
                        text = uiState.planetInfo?.planetName ?: "Planet Detail",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showPlanetSelector = true }) {
                        Text(
                            text = "Select",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            // Content
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Error loading data",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = uiState.error ?: "Unknown error",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                
                uiState.planetInfo != null -> {
                    PlanetDetailContent(
                        uiState = uiState,
                        onPlanetSelected = { planetId ->
                            viewModel.selectPlanet(planetId)
                            showPlanetSelector = false
                        }
                    )
                }
            }
        }
        
        // Planet selector dropdown
        if (showPlanetSelector) {
            PlanetSelectorDialog(
                selectedPlanetId = uiState.selectedPlanetId,
                onPlanetSelected = { planetId ->
                    viewModel.selectPlanet(planetId)
                    showPlanetSelector = false
                },
                onDismiss = { showPlanetSelector = false }
            )
        }
    }
}

@Composable
private fun PlanetDetailContent(
    uiState: PlanetDetailUiState,
    onPlanetSelected: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val planetInfo = uiState.planetInfo!!
        
        // Current Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Current Status",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                if (uiState.isInRetrograde) ActiveRetrograde
                                else InactiveRetrograde,
                                RoundedCornerShape(50)
                            )
                    )
                    Text(
                        text = if (uiState.isInRetrograde) {
                            "Currently in Retrograde"
                        } else {
                            "Currently Direct"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = if (uiState.isInRetrograde) {
                            ActiveRetrograde
                        } else {
                            InactiveRetrograde
                        }
                    )
                }
                
                if (uiState.currentPeriod != null) {
                    Text(
                        text = "Period: ${formatDate(uiState.currentPeriod.startDate)} - ${formatDate(uiState.currentPeriod.endDate)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        // Educational Content Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "About ${planetInfo.planetName} Retrograde",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = planetInfo.retrogradeExplanation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Divider()
                
                Text(
                    text = "Traditional Interpretation",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                
                Text(
                    text = planetInfo.astrologicalMeaning,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Divider()
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Typical Duration",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = planetInfo.typicalDuration,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Column {
                        Text(
                            text = "Frequency",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = planetInfo.frequency,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        
        // Upcoming Retrograde Periods
        if (uiState.upcomingPeriods.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Upcoming Retrograde Periods",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    uiState.upcomingPeriods.forEach { period ->
                        RetrogradePeriodItem(period = period)
                    }
                }
            }
        }
        
        // Past Retrograde Periods
        if (uiState.pastPeriods.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Recent Retrograde Periods",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    uiState.pastPeriods.forEach { period ->
                        RetrogradePeriodItem(period = period)
                    }
                }
            }
        }
    }
}

@Composable
private fun RetrogradePeriodItem(period: com.retronow.app.domain.model.RetrogradePeriod) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = formatDate(period.startDate),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "to ${formatDate(period.endDate)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Text(
            text = "${period.endDate.toEpochDay() - period.startDate.toEpochDay()} days",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun PlanetSelectorDialog(
    selectedPlanetId: String?,
    onPlanetSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Planet") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Planet.ALL_PLANETS.forEach { planet ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = planet.displayName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        RadioButton(
                            selected = planet.id == selectedPlanetId,
                            onClick = { onPlanetSelected(planet.id) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

private fun formatDate(date: java.time.LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
    return date.format(formatter)
}


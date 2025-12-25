package com.retronow.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.retronow.app.data.database.DatabaseProvider
import com.retronow.app.ui.theme.RetrogradeBlue
import com.retronow.app.ui.theme.RetrogradeGreen
import com.retronow.app.ui.theme.RetrogradePurple
import com.retronow.app.utils.RetrogradeCalculator

/**
 * Home screen displaying all planets and their retrograde status
 */
@Composable
fun HomeScreen(
    onMenuClick: () -> Unit = {},
    onPlanetClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            RetrogradeCalculator(
                DatabaseProvider.getRepository(LocalContext.current)
            )
        )
    )
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Background gradient overlay
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
        
        // Background image (if available)
        // Note: We'll use a gradient for now since we need to add the image to resources
        // The image from Pictures folder would need to be added to res/drawable
        
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar with menu button
            TopAppBar(
                title = {
                    Text(
                        text = "Retro Now",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
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
                            Button(onClick = { viewModel.refresh() }) {
                                Text("Retry")
                            }
                        }
                    }
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(uiState.planetStatuses) { status ->
                            PlanetTile(
                                status = status,
                                onClick = { onPlanetClick(status.planet.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}


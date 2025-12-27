package com.retronow.app.ui.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retronow.app.ui.theme.*
import android.content.Intent
import android.net.Uri

/**
 * Learn screen with educational resources about retrogrades
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(
    onBackClick: () -> Unit = {}
) {
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
        
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar
            TopAppBar(
                title = {
                    Text(
                        text = "Learn About Retrogrades",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // What is Retrograde section
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
                            text = "What is Retrograde?",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "In astronomy, retrograde motion refers to the apparent backward movement of a planet in the sky as observed from Earth. This occurs when Earth overtakes an outer planet in its orbit, or when an inner planet overtakes Earth, creating an optical illusion where the planet appears to move backward against the background of stars.",
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 24.sp
                        )
                        Text(
                            text = "While retrograde is a real astronomical phenomenon, its astrological significance is a matter of personal belief and interpretation. Many people find meaning in tracking retrograde periods as a way to reflect on different aspects of life.",
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 24.sp
                        )
                    }
                }
                
                // Educational Resources section
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
                            text = "Educational Resources",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // Resource links
                        ResourceLink(
                            title = "NASA - Planetary Motion",
                            url = "https://solarsystem.nasa.gov/planets/overview/",
                            description = "Learn about planetary motion and orbits from NASA"
                        )
                        
                        ResourceLink(
                            title = "Time and Date - Retrograde Calculator",
                            url = "https://www.timeanddate.com/astronomy/planets/retrograde.html",
                            description = "Detailed information about planetary retrogrades"
                        )
                        
                        ResourceLink(
                            title = "Astronomy.com - Understanding Retrograde",
                            url = "https://www.astronomy.com/",
                            description = "Astronomical explanations of retrograde motion"
                        )
                        
                        ResourceLink(
                            title = "Encyclopedia Britannica - Retrograde Motion",
                            url = "https://www.britannica.com/science/retrograde-motion",
                            description = "Scientific definition and history of retrograde motion"
                        )
                    }
                }
                
                // Disclaimer section
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
                            text = "Disclaimer",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "This app is for educational and informational purposes only. The content provided is not intended to predict future events or provide astrological guidance. Retrograde periods are real astronomical phenomena, but their astrological interpretations vary widely and are matters of personal belief.",
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Resource link item
 */
@Composable
private fun ResourceLink(
    title: String,
    url: String,
    description: String
) {
    val context = LocalContext.current
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = url,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }
    }
}


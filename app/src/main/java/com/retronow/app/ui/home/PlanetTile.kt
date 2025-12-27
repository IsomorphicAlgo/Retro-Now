package com.retronow.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retronow.app.ui.theme.ActiveRetrograde
import com.retronow.app.ui.theme.InactiveRetrograde

/**
 * Composable for displaying a planet's retrograde status
 */
@Composable
fun PlanetTile(
    status: PlanetStatus,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f) // Half width
                .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Square container with auto-sizing content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Makes it square
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Planet name and status
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = status.planet.displayName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    // Status indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Status dot
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (status.isInRetrograde) ActiveRetrograde
                                    else InactiveRetrograde
                                )
                        )
                        
                        Text(
                            text = if (status.isInRetrograde) {
                                "In Retrograde"
                            } else {
                                "Direct"
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (status.isInRetrograde) {
                                ActiveRetrograde
                            } else {
                                InactiveRetrograde
                            },
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                // Days remaining or next retrograde info
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (status.isInRetrograde && status.daysRemaining > 0) {
                        Text(
                            text = "${status.daysRemaining}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = ActiveRetrograde
                        )
                        Text(
                            text = "days left",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    } else if (status.currentPeriod != null) {
                        Text(
                            text = "Ends ${formatDate(status.currentPeriod.endDate)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            textAlign = TextAlign.End
                        )
                    } else {
                        Text(
                            text = "Not in retrograde",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
        }
    }
}

/**
 * Format date for display
 */
private fun formatDate(date: java.time.LocalDate): String {
    val month = date.month.toString().lowercase().take(3).replaceFirstChar { 
        if (it.isLowerCase()) it.titlecase() else it.toString() 
    }
    val day = date.dayOfMonth
    return "$month $day"
}


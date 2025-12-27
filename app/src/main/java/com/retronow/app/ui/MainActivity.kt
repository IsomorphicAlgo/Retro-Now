package com.retronow.app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.retronow.app.navigation.RetroNowNavGraph
import com.retronow.app.ui.theme.RetroNowTheme

class MainActivity : ComponentActivity() {
    private lateinit var prefs: SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("retro_now_prefs", Context.MODE_PRIVATE)
        
        // Initialize notification scheduler if notifications are enabled
        val notificationsEnabled = prefs.getBoolean("notifications_enabled", false)
        if (notificationsEnabled) {
            com.retronow.app.notifications.NotificationScheduler(this).schedulePeriodicNotifications()
        }
        
        setContent {
            val isDarkTheme = remember {
                mutableStateOf(
                    prefs.getBoolean("dark_theme", false)
                )
            }
            
            RetroNowTheme(darkTheme = isDarkTheme.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    // Handle notification intent (open planet detail if planetId is provided)
                    val planetId = intent.getStringExtra("planetId")
                    if (planetId != null) {
                        navController.navigate(com.retronow.app.navigation.Routes.planetDetail(planetId))
                    }
                    
                    RetroNowNavGraph(
                        navController = navController,
                        isDarkTheme = isDarkTheme.value,
                        onThemeToggle = { newValue ->
                            isDarkTheme.value = newValue
                            prefs.edit().putBoolean("dark_theme", newValue).apply()
                        }
                    )
                }
            }
        }
    }
}


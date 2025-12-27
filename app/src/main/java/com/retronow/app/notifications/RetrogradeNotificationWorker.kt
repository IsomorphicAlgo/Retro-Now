package com.retronow.app.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.retronow.app.data.database.DatabaseProvider
import com.retronow.app.domain.model.Planet
import com.retronow.app.utils.RetrogradeCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

/**
 * Worker that checks for upcoming retrograde periods and schedules notifications
 */
class RetrogradeNotificationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val repository = DatabaseProvider.getRepository(applicationContext)
            val calculator = RetrogradeCalculator(repository)
            val notificationManager = RetroNowNotificationManager(applicationContext)
            val prefs = applicationContext.getSharedPreferences("retro_now_prefs", Context.MODE_PRIVATE)
            
            // Check if notifications are enabled
            val notificationsEnabled = prefs.getBoolean("notifications_enabled", false)
            if (!notificationsEnabled || !notificationManager.areNotificationsEnabled()) {
                return@withContext Result.success()
            }
            
            val today = LocalDate.now()
            val tomorrow = today.plusDays(1)
            val threeDaysFromNow = today.plusDays(3)
            
            // Check all planets
            for (planet in Planet.ALL_PLANETS) {
                // Check if notifications are enabled for this planet
                val planetNotificationsEnabled = prefs.getBoolean(
                    "notifications_${planet.id}",
                    true // Default to enabled
                )
                
                if (!planetNotificationsEnabled) continue
                
                // Get upcoming retrograde periods
                val periods = repository.getRetrogradePeriods(
                    planet.id,
                    today,
                    today.plusYears(2)
                )
                
                for (period in periods) {
                    // Check for entry notifications
                    val daysUntilEntry = java.time.temporal.ChronoUnit.DAYS.between(today, period.startDate).toInt()
                    
                    if (daysUntilEntry == 0) {
                        // Entry today
                        notificationManager.sendRetrogradeEntryNotification(planet, 0)
                    } else if (daysUntilEntry == 1) {
                        // Entry tomorrow (1 day before)
                        notificationManager.sendRetrogradeEntryNotification(planet, 1)
                    } else if (daysUntilEntry == 3) {
                        // Entry in 3 days (3 days before)
                        notificationManager.sendRetrogradeEntryNotification(planet, 3)
                    }
                    
                    // Check for exit notifications
                    val daysUntilExit = java.time.temporal.ChronoUnit.DAYS.between(today, period.endDate).toInt()
                    
                    if (daysUntilExit == 0) {
                        // Exit today
                        notificationManager.sendRetrogradeExitNotification(planet, 0)
                    } else if (daysUntilExit == 1) {
                        // Exit tomorrow (1 day before)
                        notificationManager.sendRetrogradeExitNotification(planet, 1)
                    } else if (daysUntilExit == 3) {
                        // Exit in 3 days (3 days before)
                        notificationManager.sendRetrogradeExitNotification(planet, 3)
                    }
                }
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}


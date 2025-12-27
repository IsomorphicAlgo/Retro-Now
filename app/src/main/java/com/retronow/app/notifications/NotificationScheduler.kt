package com.retronow.app.notifications

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * Schedules periodic work to check for retrograde notifications
 */
class NotificationScheduler(private val context: Context) {
    
    companion object {
        private const val WORK_NAME = "retrograde_notification_check"
        private const val REPEAT_INTERVAL_HOURS = 12L // Check twice per day
    }
    
    /**
     * Schedule periodic notification checks
     */
    fun schedulePeriodicNotifications() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED) // Works offline
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(false)
            .build()
        
        val workRequest = PeriodicWorkRequestBuilder<RetrogradeNotificationWorker>(
            REPEAT_INTERVAL_HOURS,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()
        
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP, // Keep existing work if already scheduled
            workRequest
        )
    }
    
    /**
     * Cancel scheduled notifications
     */
    fun cancelScheduledNotifications() {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }
}


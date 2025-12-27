package com.retronow.app.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.retronow.app.domain.model.Planet
import com.retronow.app.ui.MainActivity

/**
 * Manages notification channels and sends notifications
 */
class RetroNowNotificationManager(private val context: Context) {
    
    companion object {
        const val CHANNEL_ID = "retrograde_notifications"
        const val CHANNEL_NAME = "Retrograde Notifications"
        const val CHANNEL_DESCRIPTION = "Notifications for planetary retrograde periods"
        
        private const val NOTIFICATION_ID_ENTRY_PREFIX = 1000
        private const val NOTIFICATION_ID_EXIT_PREFIX = 2000
        
        fun getEntryNotificationId(planetId: String): Int {
            return NOTIFICATION_ID_ENTRY_PREFIX + Planet.ALL_PLANETS.indexOfFirst { it.id == planetId }
        }
        
        fun getExitNotificationId(planetId: String): Int {
            return NOTIFICATION_ID_EXIT_PREFIX + Planet.ALL_PLANETS.indexOfFirst { it.id == planetId }
        }
    }
    
    init {
        createNotificationChannel()
    }
    
    /**
     * Create notification channel for Android O and above
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableVibration(true)
                enableLights(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    /**
     * Send notification for retrograde entry
     */
    fun sendRetrogradeEntryNotification(planet: Planet, daysUntil: Int = 0) {
        val title = if (daysUntil > 0) {
            "$daysUntil day${if (daysUntil > 1) "s" else ""} until ${planet.displayName} retrograde"
        } else {
            "${planet.displayName} enters retrograde today"
        }
        
        val message = "${planet.displayName} will ${if (daysUntil > 0) "enter" else "enters"} retrograde ${if (daysUntil > 0) "in $daysUntil day${if (daysUntil > 1) "s" else ""}" else "today"}."
        
        sendNotification(
            id = getEntryNotificationId(planet.id),
            title = title,
            message = message,
            planetId = planet.id
        )
    }
    
    /**
     * Send notification for retrograde exit
     */
    fun sendRetrogradeExitNotification(planet: Planet, daysUntil: Int = 0) {
        val title = if (daysUntil > 0) {
            "$daysUntil day${if (daysUntil > 1) "s" else ""} until ${planet.displayName} exits retrograde"
        } else {
            "${planet.displayName} exits retrograde today"
        }
        
        val message = "${planet.displayName} will ${if (daysUntil > 0) "exit" else "exits"} retrograde ${if (daysUntil > 0) "in $daysUntil day${if (daysUntil > 1) "s" else ""}" else "today"}."
        
        sendNotification(
            id = getExitNotificationId(planet.id),
            title = title,
            message = message,
            planetId = planet.id
        )
    }
    
    /**
     * Send a notification
     */
    private fun sendNotification(id: Int, title: String, message: String, planetId: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("planetId", planetId)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Using system icon for now
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            if (areNotificationsEnabled()) {
                notify(id, notification)
            }
        }
    }
    
    /**
     * Cancel a notification
     */
    fun cancelNotification(id: Int) {
        NotificationManagerCompat.from(context).cancel(id)
    }
    
    /**
     * Cancel all notifications for a planet
     */
    fun cancelPlanetNotifications(planetId: String) {
        cancelNotification(getEntryNotificationId(planetId))
        cancelNotification(getExitNotificationId(planetId))
    }
    
    /**
     * Check if notifications are enabled
     */
    fun areNotificationsEnabled(): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }
}


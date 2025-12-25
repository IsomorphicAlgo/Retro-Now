package com.retronow.app

import android.app.Application
import com.retronow.app.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RetroNowApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize database and seed data in background
        applicationScope.launch {
            DatabaseProvider.initializeDatabase(this@RetroNowApplication)
        }
    }
}


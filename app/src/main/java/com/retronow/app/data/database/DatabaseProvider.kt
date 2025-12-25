package com.retronow.app.data.database

import android.content.Context
import androidx.room.Room
import com.retronow.app.data.repository.RetrogradeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Provides database instance and repository
 */
object DatabaseProvider {
    private var INSTANCE: RetrogradeDatabase? = null
    
    fun getDatabase(context: Context): RetrogradeDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RetrogradeDatabase::class.java,
                "retrograde_database"
            )
            .build()
            INSTANCE = instance
            instance
        }
    }
    
    /**
     * Get repository instance
     */
    fun getRepository(context: Context): RetrogradeRepositoryImpl {
        val database = getDatabase(context)
        return RetrogradeRepositoryImpl(database.retrogradePeriodDao())
    }
    
    /**
     * Initialize database with seed data if needed
     */
    suspend fun initializeDatabase(context: Context) {
        withContext(Dispatchers.IO) {
            val repository = getRepository(context)
            
            // Check if database is already seeded
            val hasData = repository.hasDataForPlanet(com.retronow.app.domain.model.Planet.MERCURY.id)
            
            if (!hasData) {
                // Seed the database
                RetrogradeDataSeeder.seedDatabase(repository)
            }
        }
    }
}


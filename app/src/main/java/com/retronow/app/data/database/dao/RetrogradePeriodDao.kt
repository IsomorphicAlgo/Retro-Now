package com.retronow.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retronow.app.data.database.entity.RetrogradePeriodEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for retrograde periods
 */
@Dao
interface RetrogradePeriodDao {
    /**
     * Insert a retrograde period
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(period: RetrogradePeriodEntity)
    
    /**
     * Insert multiple retrograde periods
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(periods: List<RetrogradePeriodEntity>)
    
    /**
     * Get all retrograde periods for a specific planet
     */
    @Query("SELECT * FROM retrograde_periods WHERE planetId = :planetId ORDER BY startDate ASC")
    fun getPeriodsForPlanet(planetId: String): Flow<List<RetrogradePeriodEntity>>
    
    /**
     * Get all retrograde periods for a specific planet within a date range
     */
    @Query("""
        SELECT * FROM retrograde_periods 
        WHERE planetId = :planetId 
        AND startDate <= :endDateEpoch 
        AND endDate >= :startDateEpoch
        ORDER BY startDate ASC
    """)
    suspend fun getPeriodsForPlanetInRange(
        planetId: String,
        startDateEpoch: Long,
        endDateEpoch: Long
    ): List<RetrogradePeriodEntity>
    
    /**
     * Get the current retrograde period for a planet (if any)
     */
    @Query("""
        SELECT * FROM retrograde_periods 
        WHERE planetId = :planetId 
        AND startDate <= :dateEpoch 
        AND endDate >= :dateEpoch
        LIMIT 1
    """)
    suspend fun getCurrentPeriod(planetId: String, dateEpoch: Long): RetrogradePeriodEntity?
    
    /**
     * Get all retrograde periods within a date range (all planets)
     */
    @Query("""
        SELECT * FROM retrograde_periods 
        WHERE startDate <= :endDateEpoch 
        AND endDate >= :startDateEpoch
        ORDER BY startDate ASC
    """)
    suspend fun getAllPeriodsInRange(
        startDateEpoch: Long,
        endDateEpoch: Long
    ): List<RetrogradePeriodEntity>
    
    /**
     * Check if a planet is in retrograde on a specific date
     */
    @Query("""
        SELECT COUNT(*) > 0 FROM retrograde_periods 
        WHERE planetId = :planetId 
        AND startDate <= :dateEpoch 
        AND endDate >= :dateEpoch
    """)
    suspend fun isInRetrograde(planetId: String, dateEpoch: Long): Boolean
    
    /**
     * Delete all retrograde periods
     */
    @Query("DELETE FROM retrograde_periods")
    suspend fun deleteAll()
    
    /**
     * Get count of retrograde periods for a planet
     */
    @Query("SELECT COUNT(*) FROM retrograde_periods WHERE planetId = :planetId")
    suspend fun getCountForPlanet(planetId: String): Int
}


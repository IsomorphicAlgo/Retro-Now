package com.retronow.app.utils

import com.retronow.app.data.repository.RetrogradeRepository
import com.retronow.app.domain.model.RetrogradePeriod
import java.time.LocalDate

/**
 * Utility class for calculating and retrieving retrograde periods
 * 
 * Note: This implementation uses pre-calculated data stored in the database.
 * For real-time calculations, astronomical ephemeris libraries would be needed.
 */
class RetrogradeCalculator(
    private val repository: RetrogradeRepository
) {
    /**
     * Get retrograde periods for a planet within a date range
     * @param planetId The ID of the planet
     * @param startDate Start of the date range
     * @param endDate End of the date range
     * @return List of retrograde periods
     */
    suspend fun getRetrogradePeriods(
        planetId: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<RetrogradePeriod> {
        return repository.getRetrogradePeriods(planetId, startDate, endDate)
    }
    
    /**
     * Check if a planet is currently in retrograde
     */
    suspend fun isInRetrograde(planetId: String, date: LocalDate = LocalDate.now()): Boolean {
        return repository.isInRetrograde(planetId, date)
    }
    
    /**
     * Get the current retrograde period for a planet (if any)
     */
    suspend fun getCurrentRetrogradePeriod(
        planetId: String,
        date: LocalDate = LocalDate.now()
    ): RetrogradePeriod? {
        return repository.getCurrentRetrogradePeriod(planetId, date)
    }
}


package com.retronow.app.data.repository

import com.retronow.app.domain.model.RetrogradePeriod
import java.time.LocalDate

/**
 * Repository for managing retrograde data
 * Implementation will be completed in Stage 1
 */
interface RetrogradeRepository {
    suspend fun getRetrogradePeriods(
        planetId: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<RetrogradePeriod>
    
    suspend fun isInRetrograde(planetId: String, date: LocalDate): Boolean
    
    suspend fun getCurrentRetrogradePeriod(planetId: String, date: LocalDate): RetrogradePeriod?
}


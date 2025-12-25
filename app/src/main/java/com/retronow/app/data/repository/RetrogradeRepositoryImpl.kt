package com.retronow.app.data.repository

import com.retronow.app.data.database.dao.RetrogradePeriodDao
import com.retronow.app.data.database.entity.RetrogradePeriodEntity
import com.retronow.app.domain.model.RetrogradePeriod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

/**
 * Implementation of RetrogradeRepository
 */
class RetrogradeRepositoryImpl(
    private val retrogradePeriodDao: RetrogradePeriodDao
) : RetrogradeRepository {
    
    override suspend fun getRetrogradePeriods(
        planetId: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<RetrogradePeriod> {
        return retrogradePeriodDao.getPeriodsForPlanetInRange(
            planetId = planetId,
            startDateEpoch = startDate.toEpochDay(),
            endDateEpoch = endDate.toEpochDay()
        ).map { it.toDomainModel() }
    }
    
    override suspend fun isInRetrograde(planetId: String, date: LocalDate): Boolean {
        return retrogradePeriodDao.isInRetrograde(planetId, date.toEpochDay())
    }
    
    override suspend fun getCurrentRetrogradePeriod(
        planetId: String,
        date: LocalDate
    ): RetrogradePeriod? {
        return retrogradePeriodDao.getCurrentPeriod(planetId, date.toEpochDay())?.toDomainModel()
    }
    
    /**
     * Get all retrograde periods for a planet as a Flow
     */
    fun getPeriodsForPlanetFlow(planetId: String): Flow<List<RetrogradePeriod>> {
        return retrogradePeriodDao.getPeriodsForPlanet(planetId)
            .map { entities -> entities.map { it.toDomainModel() } }
    }
    
    /**
     * Insert retrograde periods
     */
    suspend fun insertPeriods(periods: List<RetrogradePeriod>) {
        val entities = periods.map { it.toEntity() }
        retrogradePeriodDao.insertAll(entities)
    }
    
    /**
     * Get all periods in a date range (all planets)
     */
    suspend fun getAllPeriodsInRange(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<RetrogradePeriod> {
        return retrogradePeriodDao.getAllPeriodsInRange(
            startDateEpoch = startDate.toEpochDay(),
            endDateEpoch = endDate.toEpochDay()
        ).map { it.toDomainModel() }
    }
    
    /**
     * Check if database has data for a planet
     */
    suspend fun hasDataForPlanet(planetId: String): Boolean {
        return retrogradePeriodDao.getCountForPlanet(planetId) > 0
    }
    
    // Extension functions for conversion
    private fun RetrogradePeriodEntity.toDomainModel(): RetrogradePeriod {
        return RetrogradePeriod(
            planetId = planetId,
            startDate = toLocalDateStart(),
            endDate = toLocalDateEnd()
        )
    }
    
    private fun RetrogradePeriod.toEntity(): RetrogradePeriodEntity {
        return RetrogradePeriodEntity.fromLocalDates(planetId, startDate, endDate)
    }
}


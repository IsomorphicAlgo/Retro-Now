package com.retronow.app.data.database

import com.retronow.app.data.repository.RetrogradeRepositoryImpl
import com.retronow.app.domain.model.Planet
import com.retronow.app.domain.model.RetrogradePeriod
import java.time.LocalDate

/**
 * Seeds the database with pre-calculated retrograde periods
 * 
 * Note: These are approximate dates based on typical retrograde patterns.
 * For production, use accurate astronomical calculations or verified ephemeris data.
 */
object RetrogradeDataSeeder {
    
    /**
     * Seed the database with retrograde periods for 2024-2030
     */
    suspend fun seedDatabase(repository: RetrogradeRepositoryImpl) {
        val allPeriods = mutableListOf<RetrogradePeriod>()
        
        // Mercury retrograde periods (3-4 times per year, ~3 weeks each)
        allPeriods.addAll(getMercuryRetrogradePeriods())
        
        // Venus retrograde periods (every 18 months, ~6 weeks)
        allPeriods.addAll(getVenusRetrogradePeriods())
        
        // Mars retrograde periods (every 26 months, ~2-3 months)
        allPeriods.addAll(getMarsRetrogradePeriods())
        
        // Jupiter retrograde periods (annually, ~4 months)
        allPeriods.addAll(getJupiterRetrogradePeriods())
        
        // Saturn retrograde periods (annually, ~4.5 months)
        allPeriods.addAll(getSaturnRetrogradePeriods())
        
        // Uranus retrograde periods (annually, ~5 months)
        allPeriods.addAll(getUranusRetrogradePeriods())
        
        // Neptune retrograde periods (annually, ~5 months)
        allPeriods.addAll(getNeptuneRetrogradePeriods())
        
        // Pluto retrograde periods (annually, ~5 months)
        allPeriods.addAll(getPlutoRetrogradePeriods())
        
        repository.insertPeriods(allPeriods)
    }
    
    private fun getMercuryRetrogradePeriods(): List<RetrogradePeriod> {
        // Mercury retrograde occurs 3-4 times per year, approximately every 3-4 months
        // Each period lasts about 3 weeks
        return listOf(
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 25)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2024, 8, 4), LocalDate.of(2024, 8, 28)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2024, 11, 25), LocalDate.of(2024, 12, 15)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2025, 3, 15), LocalDate.of(2025, 4, 7)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2025, 7, 18), LocalDate.of(2025, 8, 11)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2025, 11, 9), LocalDate.of(2025, 11, 29)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2026, 2, 28), LocalDate.of(2026, 3, 22)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2026, 6, 30), LocalDate.of(2026, 7, 24)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2026, 10, 26), LocalDate.of(2026, 11, 15)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2027, 2, 11), LocalDate.of(2027, 3, 5)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2027, 6, 10), LocalDate.of(2027, 7, 4)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2027, 10, 2), LocalDate.of(2027, 10, 25)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2028, 1, 25), LocalDate.of(2028, 2, 17)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2028, 5, 22), LocalDate.of(2028, 6, 15)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2028, 9, 18), LocalDate.of(2028, 10, 11)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2029, 1, 8), LocalDate.of(2029, 1, 31)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2029, 5, 4), LocalDate.of(2029, 5, 28)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2029, 9, 4), LocalDate.of(2029, 9, 27)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2029, 12, 22), LocalDate.of(2030, 1, 14)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2030, 4, 18), LocalDate.of(2030, 5, 11)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2030, 8, 20), LocalDate.of(2030, 9, 12)),
            RetrogradePeriod(Planet.MERCURY.id, LocalDate.of(2030, 12, 8), LocalDate.of(2030, 12, 29))
        )
    }
    
    private fun getVenusRetrogradePeriods(): List<RetrogradePeriod> {
        // Venus retrograde occurs approximately every 18 months, lasting about 6 weeks
        return listOf(
            RetrogradePeriod(Planet.VENUS.id, LocalDate.of(2024, 12, 20), LocalDate.of(2025, 1, 29)),
            RetrogradePeriod(Planet.VENUS.id, LocalDate.of(2026, 6, 2), LocalDate.of(2026, 7, 11)),
            RetrogradePeriod(Planet.VENUS.id, LocalDate.of(2027, 11, 13), LocalDate.of(2027, 12, 22)),
            RetrogradePeriod(Planet.VENUS.id, LocalDate.of(2029, 4, 25), LocalDate.of(2029, 6, 3))
        )
    }
    
    private fun getMarsRetrogradePeriods(): List<RetrogradePeriod> {
        // Mars retrograde occurs approximately every 26 months, lasting 2-3 months
        return listOf(
            RetrogradePeriod(Planet.MARS.id, LocalDate.of(2024, 12, 6), LocalDate.of(2025, 2, 23)),
            RetrogradePeriod(Planet.MARS.id, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 4, 30)),
            RetrogradePeriod(Planet.MARS.id, LocalDate.of(2029, 4, 16), LocalDate.of(2029, 7, 5))
        )
    }
    
    private fun getJupiterRetrogradePeriods(): List<RetrogradePeriod> {
        // Jupiter retrograde occurs annually, lasting about 4 months
        return listOf(
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2024, 10, 9), LocalDate.of(2025, 2, 4)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2025, 10, 5), LocalDate.of(2026, 1, 30)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2026, 10, 1), LocalDate.of(2027, 1, 25)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2027, 9, 27), LocalDate.of(2028, 1, 21)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2028, 9, 23), LocalDate.of(2029, 1, 17)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2029, 9, 19), LocalDate.of(2030, 1, 13)),
            RetrogradePeriod(Planet.JUPITER.id, LocalDate.of(2030, 9, 15), LocalDate.of(2031, 1, 9))
        )
    }
    
    private fun getSaturnRetrogradePeriods(): List<RetrogradePeriod> {
        // Saturn retrograde occurs annually, lasting about 4.5 months
        return listOf(
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2024, 6, 29), LocalDate.of(2024, 11, 15)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2025, 6, 25), LocalDate.of(2025, 11, 11)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2026, 6, 21), LocalDate.of(2026, 11, 7)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2027, 6, 17), LocalDate.of(2027, 11, 3)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2028, 6, 13), LocalDate.of(2028, 10, 30)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2029, 6, 9), LocalDate.of(2029, 10, 26)),
            RetrogradePeriod(Planet.SATURN.id, LocalDate.of(2030, 6, 5), LocalDate.of(2030, 10, 22))
        )
    }
    
    private fun getUranusRetrogradePeriods(): List<RetrogradePeriod> {
        // Uranus retrograde occurs annually, lasting about 5 months
        return listOf(
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2024, 8, 28), LocalDate.of(2025, 1, 27)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2025, 8, 24), LocalDate.of(2026, 1, 23)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2026, 8, 20), LocalDate.of(2027, 1, 19)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2027, 8, 16), LocalDate.of(2028, 1, 15)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2028, 8, 12), LocalDate.of(2029, 1, 11)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2029, 8, 8), LocalDate.of(2030, 1, 7)),
            RetrogradePeriod(Planet.URANUS.id, LocalDate.of(2030, 8, 4), LocalDate.of(2031, 1, 3))
        )
    }
    
    private fun getNeptuneRetrogradePeriods(): List<RetrogradePeriod> {
        // Neptune retrograde occurs annually, lasting about 5 months
        return listOf(
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2024, 7, 2), LocalDate.of(2024, 12, 1)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2025, 6, 28), LocalDate.of(2025, 11, 27)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2026, 6, 24), LocalDate.of(2026, 11, 23)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2027, 6, 20), LocalDate.of(2027, 11, 19)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2028, 6, 16), LocalDate.of(2028, 11, 15)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2029, 6, 12), LocalDate.of(2029, 11, 11)),
            RetrogradePeriod(Planet.NEPTUNE.id, LocalDate.of(2030, 6, 8), LocalDate.of(2030, 11, 7))
        )
    }
    
    private fun getPlutoRetrogradePeriods(): List<RetrogradePeriod> {
        // Pluto retrograde occurs annually, lasting about 5 months
        return listOf(
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 10, 11)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2025, 4, 28), LocalDate.of(2025, 10, 7)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2026, 4, 24), LocalDate.of(2026, 10, 3)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2027, 4, 20), LocalDate.of(2027, 9, 29)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2028, 4, 16), LocalDate.of(2028, 9, 25)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2029, 4, 12), LocalDate.of(2029, 9, 21)),
            RetrogradePeriod(Planet.PLUTO.id, LocalDate.of(2030, 4, 8), LocalDate.of(2030, 9, 17))
        )
    }
}


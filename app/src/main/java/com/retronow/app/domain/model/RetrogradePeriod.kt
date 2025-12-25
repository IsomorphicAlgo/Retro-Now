package com.retronow.app.domain.model

import java.time.LocalDate

/**
 * Represents a period when a planet is in retrograde
 */
data class RetrogradePeriod(
    val planetId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) {
    /**
     * Check if the planet is currently in retrograde on the given date
     */
    fun isActiveOn(date: LocalDate): Boolean {
        return !date.isBefore(startDate) && !date.isAfter(endDate)
    }
    
    /**
     * Get the number of days remaining in retrograde from the given date
     */
    fun daysRemaining(fromDate: LocalDate): Int {
        return if (isActiveOn(fromDate)) {
            endDate.toEpochDay().toInt() - fromDate.toEpochDay().toInt()
        } else {
            0
        }
    }
}


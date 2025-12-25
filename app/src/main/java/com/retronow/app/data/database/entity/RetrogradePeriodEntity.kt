package com.retronow.app.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * Room entity for storing retrograde periods in the database
 */
@Entity(tableName = "retrograde_periods")
data class RetrogradePeriodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val planetId: String,
    val startDate: Long, // Epoch days
    val endDate: Long    // Epoch days
) {
    fun toLocalDateStart(): LocalDate = LocalDate.ofEpochDay(startDate)
    fun toLocalDateEnd(): LocalDate = LocalDate.ofEpochDay(endDate)
    
    companion object {
        fun fromLocalDates(planetId: String, startDate: LocalDate, endDate: LocalDate): RetrogradePeriodEntity {
            return RetrogradePeriodEntity(
                planetId = planetId,
                startDate = startDate.toEpochDay(),
                endDate = endDate.toEpochDay()
            )
        }
    }
}


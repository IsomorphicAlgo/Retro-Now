package com.retronow.app.data.database

import androidx.room.TypeConverter
import java.time.LocalDate

/**
 * Type converters for Room database
 */
class Converters {
    @TypeConverter
    fun fromEpochDay(epochDay: Long): LocalDate {
        return LocalDate.ofEpochDay(epochDay)
    }
    
    @TypeConverter
    fun toEpochDay(date: LocalDate): Long {
        return date.toEpochDay()
    }
}


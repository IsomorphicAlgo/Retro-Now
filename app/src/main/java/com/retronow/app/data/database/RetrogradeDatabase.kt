package com.retronow.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.retronow.app.data.database.dao.RetrogradePeriodDao
import com.retronow.app.data.database.entity.RetrogradePeriodEntity

@Database(
    entities = [RetrogradePeriodEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RetrogradeDatabase : RoomDatabase() {
    abstract fun retrogradePeriodDao(): RetrogradePeriodDao
}


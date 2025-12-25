package com.retronow.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class RetrogradeDatabase : RoomDatabase() {
    // DAOs will be added here in Stage 1
}


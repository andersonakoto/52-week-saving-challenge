package com.example.a52weeksaver.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a52weeksaver.db.daos.SavingsDao
import com.example.a52weeksaver.db.entities.SavingsData

@Database(
    entities =
    [
        SavingsData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SaverDatabase : RoomDatabase() {

    abstract fun savingsDao(): SavingsDao
}

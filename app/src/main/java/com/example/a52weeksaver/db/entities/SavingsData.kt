package com.example.a52weeksaver.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savings_table")
data class SavingsData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recordId") var recordId: Int = 0,
    @ColumnInfo(name = "weekNumber") var weekNumber: Int? = 0,
    @ColumnInfo(name = "timeStamp") var timeStamp: String = "",
    @ColumnInfo(name = "savedAmount") var savedAmount: Double? = 0.0,
    @ColumnInfo(name = "mpesaRef") var mpesaRef: String = ""
)
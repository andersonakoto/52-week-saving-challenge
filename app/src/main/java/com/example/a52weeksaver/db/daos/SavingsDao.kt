package com.example.a52weeksaver.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a52weeksaver.db.entities.SavingsData
import kotlinx.coroutines.flow.Flow

@Dao
interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertSavingsData(savingsData: SavingsData)

    @Query("SELECT * FROM savings_table order by weekNumber desc")
    fun getSavingsData() : Flow<List<SavingsData>>

}
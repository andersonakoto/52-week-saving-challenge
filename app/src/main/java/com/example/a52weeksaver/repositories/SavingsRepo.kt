package com.example.a52weeksaver.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.a52weeksaver.db.daos.SavingsDao
import com.example.a52weeksaver.db.entities.SavingsData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavingsRepo @Inject constructor(private val savingsDao: SavingsDao) {

    val allSavings: LiveData<List<SavingsData>> = savingsDao.getSavingsData().asLiveData()

    suspend fun insert(savingsData: SavingsData) {
        savingsDao.insertSavingsData(savingsData)
    }
}
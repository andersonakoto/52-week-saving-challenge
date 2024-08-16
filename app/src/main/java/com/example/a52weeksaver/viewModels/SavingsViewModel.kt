package com.example.a52weeksaver.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a52weeksaver.db.entities.SavingsData
import com.example.a52weeksaver.repositories.SavingsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavingsViewModel @Inject constructor(
    private val savingsRepo: SavingsRepo
): ViewModel() {

    fun insertSavingsData(savingsData: SavingsData) {
        viewModelScope.launch(Dispatchers.IO) {
            savingsRepo.insert(savingsData)
        }
    }

    val allSavingsData = savingsRepo.allSavings
}
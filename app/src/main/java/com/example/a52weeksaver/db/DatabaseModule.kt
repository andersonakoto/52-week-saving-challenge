package com.example.a52weeksaver.db

import android.content.Context
import androidx.room.Room
import com.example.a52weeksaver.db.daos.SavingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideSavingsDao(saverDatabase: SaverDatabase): SavingsDao {
        return saverDatabase.savingsDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): SaverDatabase {
        return Room.databaseBuilder(
            appContext,
            SaverDatabase::class.java,
            "saverDB")
            .fallbackToDestructiveMigration()
            .build()
    }
}
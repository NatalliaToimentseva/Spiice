package com.example.spiice.di

import android.content.Context
import androidx.room.Room
import com.example.spiice.roomDB.AppDatabase
import com.example.spiice.roomDB.dao.AccountDao
import com.example.spiice.roomDB.dao.NotesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATA_BASE_NAME = "app_data_base"

@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDB(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATA_BASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(appDatabase: AppDatabase): NotesDao {
        return appDatabase.getNotesDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.getAccountDao()
    }
}
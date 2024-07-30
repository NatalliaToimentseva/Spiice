package com.example.spiice.di

import android.content.Context
import androidx.room.Room
import com.example.spiice.roomDB.AppDatabase
import com.example.spiice.roomDB.dao.AccountDao
import com.example.spiice.roomDB.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_data_base")
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
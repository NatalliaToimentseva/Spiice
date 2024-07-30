package com.example.spiice.roomDB

import android.content.Context
import androidx.room.Room
import com.example.spiice.roomDB.dao.AccountDao
import com.example.spiice.roomDB.dao.NotesDao

object DataBaseProvider {

    var accountDao: AccountDao? = null
    var notesDao: NotesDao? = null

    fun init(context: Context) {
        val database: AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "app_data_base")
                .allowMainThreadQueries()
                .build()
        accountDao = database.getAccountDao()
        notesDao = database.getNotesDao()
    }
}
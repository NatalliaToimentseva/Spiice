package com.example.spiice.roomDB

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spiice.roomDB.dao.AccountDao
import com.example.spiice.roomDB.dao.NotesDao
import com.example.spiice.roomDB.entities.AccountDbEntity
import com.example.spiice.roomDB.entities.NoteDbEntity
import com.example.spiice.utils.DateConverter

@Database(
    version = 2,
    entities = [
        AccountDbEntity::class,
        NoteDbEntity::class
    ],
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = AutoMigrationSpec1To2::class
        )
    ]
)
@TypeConverters(value = [DateConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDao

    abstract fun getNotesDao(): NotesDao
}
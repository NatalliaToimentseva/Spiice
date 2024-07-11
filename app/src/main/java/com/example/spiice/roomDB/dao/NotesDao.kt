package com.example.spiice.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.spiice.roomDB.entities.NoteDbEntity

@Dao
interface NotesDao {

    @Insert
    suspend fun createNote(noteDbEntity: NoteDbEntity)

    @Query("SELECT * FROM Notes WHERE user_email == :email ")
    suspend fun getNotes(email: String): List<NoteDbEntity>
}
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

    @Query("DELETE FROM Notes WHERE user_email == :userEmail")
    suspend fun deleteUserNotes(userEmail: String)

    @Query ("Select * FROM Notes Where user_email == :userEmail AND title LIKE '%' || :query || '%' OR message LIKE '%' || :query || '%'")
    suspend fun searchInNotes(query: String, userEmail: String): List<NoteDbEntity>
}
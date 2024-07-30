package com.example.spiice.repositoty

import com.example.spiice.models.noteModel.Note
import java.time.LocalDate

interface NotesRepository {

    suspend fun getListNotes(userEmail: String): List<Note>

    suspend fun addSimpleNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        message: String
    )

    suspend fun addScheduledNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    )

    suspend fun deleteAllNotes(userEmail: String)

    suspend fun searchInNotes(query: String, email: String): List<Note>
}
package com.example.spiice.roomDB.repository

import com.example.spiice.models.noteModel.Note
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.roomDB.DataBaseProvider
import com.example.spiice.roomDB.entities.NoteDbEntity
import com.example.spiice.utils.toNoteList
import java.time.LocalDate

class NotesRoomDBRepository : NotesRepository {
    override suspend fun getListNotes(userEmail: String): List<Note> {
        return DataBaseProvider.notesDao?.getNotes(userEmail)?.toNoteList() ?: arrayListOf()
    }

    override suspend fun addSimpleNote(userEmail: String, title: String, addedData: LocalDate, message: String) {
        DataBaseProvider.notesDao?.createNote(
            NoteDbEntity(
                id = 0,
                userEmail = userEmail,
                title = title,
                message = message,
                addedData = addedData,
                scheduledData = null
            )
        )
    }

    override suspend fun addScheduledNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    ) {
        DataBaseProvider.notesDao?.createNote(
            NoteDbEntity(
                id = 0,
                userEmail = userEmail,
                title = title,
                message = message,
                addedData = addedData,
                scheduledData = scheduledData
            )
        )
    }
}
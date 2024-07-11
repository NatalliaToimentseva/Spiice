package com.example.spiice.roomDB.repository

import com.example.spiice.models.noteModel.Note
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.roomDB.dao.NotesDao
import com.example.spiice.roomDB.entities.NoteDbEntity
import com.example.spiice.utils.toNoteList
import java.time.LocalDate
import javax.inject.Inject

class NotesRoomDBRepository @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override suspend fun getListNotes(userEmail: String): List<Note> {
        return notesDao.getNotes(userEmail).toNoteList() ?: arrayListOf()
    }

    override suspend fun addSimpleNote(userEmail: String, title: String, addedData: LocalDate, message: String) {
        notesDao.createNote(
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
        notesDao.createNote(
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
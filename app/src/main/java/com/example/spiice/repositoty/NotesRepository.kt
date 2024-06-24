package com.example.spiice.repositoty

import com.example.spiice.entities.noteEntity.NoteEntity
import com.example.spiice.entities.noteEntity.ScheduledNoteEntity
import com.example.spiice.localDB.InMemoryNotesList
import java.time.LocalDate

class NotesRepository {

    fun getListNotes() = InMemoryNotesList.getNotes()

    fun addSimpleNote(title: String, addedData: LocalDate, message: String) {
        InMemoryNotesList.setNotes(
            NoteEntity(
                id = setID(),
                title = title,
                addedData = addedData,
                message = message,
            )
        )
    }

    fun addScheduledNote(
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    ) {
        InMemoryNotesList.setNotes(
            ScheduledNoteEntity(
                id = setID(),
                title = title,
                addedData = addedData,
                scheduledData = scheduledData,
                message = message,
            )
        )
    }

    private fun setID() = InMemoryNotesList.getNotes().size.toLong()
}
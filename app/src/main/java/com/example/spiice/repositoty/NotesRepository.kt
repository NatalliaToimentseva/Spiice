package com.example.spiice.repositoty

import com.example.spiice.models.noteModel.Note
import java.time.LocalDate

interface NotesRepository {

    fun getListNotes(userEmail: String): List<Note>

    fun addSimpleNote(userEmail: String, title: String, addedData: LocalDate, message: String)

    fun addScheduledNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    )
}
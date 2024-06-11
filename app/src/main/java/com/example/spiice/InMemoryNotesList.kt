package com.example.spiice

import com.example.spiice.entities.NoteEntity
import java.time.LocalDate

object InMemoryNotesList {

    private var notesList = listOf(
        //Test
        NoteEntity(
            "FirstTestNote",
            LocalDate.of(2024, 6, 7),
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        ),
        NoteEntity(
            "SecondTestNote",
            LocalDate.of(2024,6,8),
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
        ),
        NoteEntity(
            "ThirdTestNote",
            LocalDate.of(2024,6,1),
            "Lorem Ipsum has been the industry's standard"
        ),
    )

    fun getNotes(): List<NoteEntity> {
        return notesList
    }

    fun setNotes(noteEntity: NoteEntity) {
        notesList = notesList.plus(noteEntity)
    }
}
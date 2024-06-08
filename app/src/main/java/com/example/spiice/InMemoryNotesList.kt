package com.example.spiice

import com.example.spiice.entities.NoteEntity

object InMemoryNotesList {

    private var notesList = listOf(
        NoteEntity(
            "FirstTestNote",
            "Jun 7, 2024",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        ),
        NoteEntity(
            "SecondTestNote",
            "Jun 8, 2024",
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
        ),
        NoteEntity("ThirdTestNote", "Jun 1, 2024", "Lorem Ipsum has been the industry's standard"),
    )

    fun getNotes(): List<NoteEntity> {
        return notesList
    }

    fun setNotes (noteEntity: NoteEntity) {
        notesList = notesList.plus(noteEntity)
    }
}
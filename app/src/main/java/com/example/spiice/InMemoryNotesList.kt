package com.example.spiice

import com.example.spiice.entities.Note
import com.example.spiice.entities.NoteEntity
import com.example.spiice.entities.ScheduledNoteEntity
import com.example.spiice.entities.Subscriber
import java.time.LocalDate

object InMemoryNotesList {

    private var notesList: List<Note> = listOf(
        //Test
        NoteEntity(
            "FirstTestNote",
            LocalDate.of(2024, 6, 7),
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        ),
        ScheduledNoteEntity(
            "SecondTestNote",
            LocalDate.of(2024, 5, 8),
            LocalDate.of(2024, 6, 1),
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
        ),
        NoteEntity(
            "ThirdTestNote",
            LocalDate.of(2024, 6, 1),
            "Lorem Ipsum has been the industry's standard"
        ),
    )

    private val subscribers = arrayListOf<Subscriber>()

    fun getNotes(): List<Note> {
        return notesList
    }

    fun setNotes(noteEntity: Note) {
        notesList = notesList.plus(noteEntity)
        notifySubscribers()
    }

    fun addSubscriber(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    fun removeSubscriber(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    private fun notifySubscribers() {
        subscribers.forEach {
            it.update()
        }
    }
}
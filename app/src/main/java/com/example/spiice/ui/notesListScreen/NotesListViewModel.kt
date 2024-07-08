package com.example.spiice.ui.notesListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.entities.noteEntity.Note
import com.example.spiice.localDB.InMemoryNotesList
import com.example.spiice.localDB.Subscriber
import com.example.spiice.repositoty.NotesRepository

class NotesListViewModel : ViewModel(), Subscriber {

    private var _notesList = MutableLiveData<List<Note>>()
    val notesList get() = _notesList

    private val notesRepository = NotesRepository()

    init {
        InMemoryNotesList.addSubscriber(this)
    }

    override fun onCleared() {
        super.onCleared()
        InMemoryNotesList.removeSubscriber(this)
    }

    override fun update() {
        getNotesList()
    }

    fun getNotesList() {
        _notesList.value = notesRepository.getListNotes()
    }
}
package com.example.spiice.ui.notesListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.models.noteModel.Note
import com.example.spiice.repositoty.RepositoryProvider

class NotesListViewModel : ViewModel() {

    private var _notesList = MutableLiveData<List<Note>>()
    val notesList get() = _notesList

    private var userEmail = MutableLiveData<String>()

    private val notesRepository = RepositoryProvider.getNotesRepository()

    fun addEmail(email: String) {
        userEmail.value = email
    }

    fun getNotesList() {
        _notesList.value = userEmail.value?.let { notesRepository.getListNotes(it) }
    }
}
package com.example.spiice.ui.notesListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.models.noteModel.Note
import com.example.spiice.repositoty.RepositoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListViewModel : ViewModel() {

    private var _notesList = MutableLiveData<List<Note>>()
    val notesList get() = _notesList

    private var userEmail = MutableLiveData<String>()

    private val notesRepository = RepositoryProvider.getNotesRepository()

    fun addEmail(email: String) {
        userEmail.value = email
    }

    fun getNotesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _notesList.postValue(userEmail.value?.let { notesRepository.getListNotes(it) })
        }
    }
}
package com.example.spiice.ui.notesListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.models.noteModel.Note
import com.example.spiice.repositoty.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor (
    private val notesRepository: NotesRepository
) : ViewModel() {

    private var _notesList = MutableLiveData<List<Note>>()
    val notesList get() = _notesList

    private var userEmail = MutableLiveData<String>()

    fun addEmail(email: String) {
        userEmail.value = email
    }

    fun getNotesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _notesList.postValue(userEmail.value?.let { notesRepository.getListNotes(it) })
        }
    }
}
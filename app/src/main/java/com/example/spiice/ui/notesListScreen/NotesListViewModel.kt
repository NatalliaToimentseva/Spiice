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
class NotesListViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private var _notesList = MutableLiveData<List<Note>>()
    val notesList get() = _notesList

    fun getNotesList(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _notesList.postValue(notesRepository.getListNotes(email))
        }
    }
}
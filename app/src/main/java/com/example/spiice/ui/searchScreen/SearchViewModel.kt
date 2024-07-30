package com.example.spiice.ui.searchScreen

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
class SearchViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private var _searchNoteList = MutableLiveData<List<Note>>()
    val searchNoteList get() = _searchNoteList

    fun getSearchNotes(query: String, email: String) {
        if(query.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                _searchNoteList.postValue(notesRepository.searchInNotes(query, email))
            }
        } else _searchNoteList.value = arrayListOf()
    }
}
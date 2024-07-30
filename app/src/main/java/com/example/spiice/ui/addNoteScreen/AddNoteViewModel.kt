package com.example.spiice.ui.addNoteScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.utils.convertDataFromLocalDataToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor (
    private val notesRepository: NotesRepository
): ViewModel() {

    private var _dataPickerVisibility = MutableLiveData(false)
    val dataPickerVisibility get() = _dataPickerVisibility

    private var _dataPickerData = MutableLiveData(convertDataFromLocalDataToString(LocalDate.now()))
    val dataPickerData get() = _dataPickerData

    private var _progressBarVisibility = MutableLiveData(false)
    val progressBarVisibility get() = _progressBarVisibility

    fun setPickerVisibility(visibility: Boolean) {
        _dataPickerVisibility.value = visibility
    }

    fun setData(fieldData: String) {
        _dataPickerData.value = fieldData
    }

    fun setSimpleNote(userEmail: String, title: String, addedData: LocalDate, message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            notesRepository.addSimpleNote(userEmail, title, addedData, message)
            _progressBarVisibility.postValue(false)
        }
    }

    fun setScheduledNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.postValue(true)
            notesRepository.addScheduledNote(userEmail, title, addedData, scheduledData, message)
            _progressBarVisibility.postValue(false)
        }
    }
}
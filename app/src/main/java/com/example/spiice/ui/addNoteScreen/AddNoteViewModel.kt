package com.example.spiice.ui.addNoteScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.utils.convertDataFromLocalDataToString
import java.time.LocalDate

class AddNoteViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    private var _dataPickerVisibility = MutableLiveData(false)
    val dataPickerVisibility get() = _dataPickerVisibility

    private var _dataPickerData = MutableLiveData(convertDataFromLocalDataToString(LocalDate.now()))
    val dataPickerData get() = _dataPickerData

    fun setPickerVisibility (visibility: Boolean) {
        _dataPickerVisibility.value = visibility
    }

    fun setData(fieldData: String) {
        _dataPickerData.value = fieldData
    }

    fun setSimpleNote(title: String, addedData: LocalDate, message: String) {
        notesRepository.addSimpleNote(title, addedData, message)
    }

    fun setScheduledNote(
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    ) {
        notesRepository.addScheduledNote(title, addedData, scheduledData, message)
    }
}
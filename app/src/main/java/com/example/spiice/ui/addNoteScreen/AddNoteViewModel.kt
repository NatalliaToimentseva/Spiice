package com.example.spiice.ui.addNoteScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.repositoty.RepositoryProvider
import com.example.spiice.utils.convertDataFromLocalDataToString
import java.time.LocalDate

class AddNoteViewModel : ViewModel() {

    private val notesRepository = RepositoryProvider.getNotesRepository()

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

    fun setSimpleNote(userEmail: String, title: String, addedData: LocalDate, message: String) {
        notesRepository.addSimpleNote(userEmail, title, addedData, message)
    }

    fun setScheduledNote(
        userEmail: String,
        title: String,
        addedData: LocalDate,
        scheduledData: LocalDate,
        message: String
    ) {
        notesRepository.addScheduledNote(userEmail, title, addedData, scheduledData, message)
    }
}
package com.example.spiice.ui.profileScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.roomDB.entities.UserTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EMPTY_NOTE = 0

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val accountRepository: AccountRepository
): ViewModel() {

    private var _isDeleted = MutableLiveData(false)
    val isDeleted get() = _isDeleted

    private var _userFullName = MutableLiveData<UserTuple?>()
    val userFullName get() = _userFullName

    private var _userNotes = MutableLiveData(0)
    val userNote get() = _userNotes

    fun getNotesCount(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userNotes.postValue(notesRepository.getListNotes(email).size)
        }
    }

    fun getUserFullName(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userFullName.postValue(accountRepository.getUserName(email))
        }
    }

    fun deleteAccount(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.deleteAccountByEmail(email)
            _isDeleted.postValue(true)
        }
    }

    fun deleteAllUserNotes(userEmail: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllNotes(userEmail)
            _userNotes.postValue(EMPTY_NOTE)
        }
    }
}
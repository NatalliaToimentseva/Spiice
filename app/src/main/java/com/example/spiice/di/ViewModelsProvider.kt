package com.example.spiice.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.ui.addNoteScreen.AddNoteViewModel
import com.example.spiice.ui.logInScreen.LogInViewModel
import com.example.spiice.ui.notesListScreen.NotesListViewModel
import com.example.spiice.ui.profileScreen.ProfileViewModel
import com.example.spiice.ui.searchScreen.SearchViewModel
import com.example.spiice.ui.signUpScreen.SignUpViewModel
import javax.inject.Inject

class ViewModelsProvider @Inject constructor(
    private val notesRepository: NotesRepository,
    private val accountRepository: AccountRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddNoteViewModel::class.java) -> {
                AddNoteViewModel(notesRepository)
            }
            modelClass.isAssignableFrom(LogInViewModel::class.java) -> {
                LogInViewModel(accountRepository)
            }
            modelClass.isAssignableFrom(NotesListViewModel::class.java) -> {
                NotesListViewModel(notesRepository)
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(notesRepository, accountRepository)
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(notesRepository)
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(accountRepository)
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}
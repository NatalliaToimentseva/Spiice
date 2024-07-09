package com.example.spiice.repositoty

import com.example.spiice.roomDB.repository.AccountRoomDbRepository
import com.example.spiice.roomDB.repository.NotesRoomDBRepository

object RepositoryProvider {

    private val accountRepository: AccountRoomDbRepository = AccountRoomDbRepository()
    private val notesRepository: NotesRoomDBRepository = NotesRoomDBRepository()

    fun getAccountRepository() = accountRepository
    fun getNotesRepository() = notesRepository
}
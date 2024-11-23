package com.example.spiice.di

import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.roomDB.repository.AccountRoomDbRepository
import com.example.spiice.roomDB.repository.NotesRoomDBRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        notesRepository: NotesRoomDBRepository
    ): NotesRepository

    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        accountRepository: AccountRoomDbRepository
    ): AccountRepository
}
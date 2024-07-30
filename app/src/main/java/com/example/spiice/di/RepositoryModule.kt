package com.example.spiice.di

import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.repositoty.NotesRepository
import com.example.spiice.roomDB.repository.AccountRoomDbRepository
import com.example.spiice.roomDB.repository.NotesRoomDBRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        notesRepository: NotesRoomDBRepository
    ): NotesRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AccountRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        accountRepository: AccountRoomDbRepository
    ): AccountRepository
}
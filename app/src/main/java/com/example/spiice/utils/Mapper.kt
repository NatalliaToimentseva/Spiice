package com.example.spiice.utils

import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.roomDB.entities.AccountDbEntity
import com.example.spiice.roomDB.entities.NoteDbEntity
import com.example.spiice.utils.securityUtils.SecurityUtils
import javax.inject.Inject

@Inject
lateinit var securityUtils: SecurityUtils

fun NoteDbEntity.toNote(): Note {
    return if (this.scheduledData != null) {
        return ScheduledNote(
            id = id,
            title = title,
            message = message,
            addedData = addedData,
            scheduledData = scheduledData
        )
    } else SimpleNote(
        id = id,
        title = title,
        message = message,
        addedData = addedData,
    )
}

fun List<NoteDbEntity>.toNoteList(): List<Note> {
    return map { noteDbEntity ->
        noteDbEntity.toNote()
    }
}

fun SignUpAccountData.toAccountDBEntity(): AccountDbEntity {
    val salt = securityUtils.generateSalt()
    val hash = securityUtils.passwordToHash(password, salt)
    return AccountDbEntity(
        id = 0,
        firstName = name,
        lastName = surname,
        email = email,
        hash = securityUtils.bytesToString(hash),
        salt = securityUtils.bytesToString(salt)
    )
}
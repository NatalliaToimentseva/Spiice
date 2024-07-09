package com.example.spiice.utils

import com.example.spiice.models.accountModel.LoginAccountData
import com.example.spiice.models.noteModel.Note
import com.example.spiice.models.noteModel.ScheduledNote
import com.example.spiice.models.noteModel.SimpleNote
import com.example.spiice.roomDB.entities.AccountLogInTuple
import com.example.spiice.roomDB.entities.NoteDbEntity

fun AccountLogInTuple.toLoginAccountData(): LoginAccountData {
    return LoginAccountData(id, email, password)
}

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
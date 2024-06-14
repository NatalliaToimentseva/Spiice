package com.example.spiice.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
sealed class Note : Parcelable

@Parcelize
data class NoteEntity(
    val title: String,
    val addedData: LocalDate,
    val message: String,
) : Note()

@Parcelize
data class ScheduledNoteEntity(
    val title: String,
    val addedData: LocalDate,
    val scheduledData: LocalDate,
    val message: String,
) : Note()
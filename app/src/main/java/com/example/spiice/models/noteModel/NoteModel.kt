package com.example.spiice.models.noteModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
sealed class Note : Parcelable

@Parcelize
data class SimpleNote(
    val id: Long,
    val title: String,
    val addedDate: LocalDate,
    val message: String,
) : Note()

@Parcelize
data class ScheduledNote(
    val id: Long,
    val title: String,
    val addedDate: LocalDate,
    val scheduledDate: LocalDate,
    val message: String,
) : Note()

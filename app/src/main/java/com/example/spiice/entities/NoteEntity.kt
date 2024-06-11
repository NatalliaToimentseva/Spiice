package com.example.spiice.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class NoteEntity(
    val title: String,
    val startingData: LocalDate,
    val message: String,
) : Parcelable
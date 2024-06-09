package com.example.spiice.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteEntity(
    val title: String,
    val startingData: String,
    val message: String,
) : Parcelable
package com.example.spiice.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "Notes",
    foreignKeys = [
        ForeignKey(
            entity = AccountDbEntity::class,
            parentColumns = ["email"],
            childColumns = ["user_email"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class NoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("user_email")
    val userEmail: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("message")
    val message: String,
    @ColumnInfo("added_at")
    val addedData: LocalDate,
    @ColumnInfo("scheduled_at")
    val scheduledData: LocalDate?
)

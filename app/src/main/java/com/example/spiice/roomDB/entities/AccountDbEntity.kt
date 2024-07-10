package com.example.spiice.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
    @ColumnInfo("email", collate = ColumnInfo.NOCASE)
    val email: String,
    @ColumnInfo("hash")
    val hash: String,
    @ColumnInfo("salt", defaultValue = "1")
    val salt: String,
)
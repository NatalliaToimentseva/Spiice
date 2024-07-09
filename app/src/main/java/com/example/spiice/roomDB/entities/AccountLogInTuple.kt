package com.example.spiice.roomDB.entities

import androidx.room.ColumnInfo

data class AccountLogInTuple(
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("hash")
    val hash: String,
    @ColumnInfo("salt", defaultValue = "")
    val salt: String,
)

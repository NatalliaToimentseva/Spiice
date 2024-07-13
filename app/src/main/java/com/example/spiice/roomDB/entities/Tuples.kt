package com.example.spiice.roomDB.entities

import androidx.room.ColumnInfo

data class AccountLogInTuple(
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("hash")
    val hash: String,
    @ColumnInfo("salt")
    val salt: String,
)

data class UserTuple(
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
)

fun UserTuple.getFullName(): String {
    return "$firstName $lastName"
}


